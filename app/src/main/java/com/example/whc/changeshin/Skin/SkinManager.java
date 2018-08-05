package com.example.whc.changeshin.Skin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.example.whc.changeshin.Skin.CallBack.ISkinChangeCallBack;
import com.example.whc.changeshin.Skin.CallBack.ISkinChangedListener;
import com.example.whc.changeshin.Skin.attr.SkinView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WSY on 2018/6/8.
 */

public class SkinManager {

    private Context context;
    private static SkinManager skinManager;
    private ResourceManager resourceManager;

    private List<ISkinChangedListener> mListeners = new ArrayList<>();
    private Map<ISkinChangedListener, List<SkinView>> mSkinViewMaps = new HashMap<>();

    private PrefUtils prefUtils;
    private String mCurPath;
    private String mCurPkg;
    private String mSuffix;

    private SkinManager() {

    }

    public static SkinManager getInstance() {
        if (skinManager == null) {
            synchronized (SkinManager.class) {
                if (skinManager == null) {
                    skinManager = new SkinManager();
                }
            }
        }
        return skinManager;
    }

    public void init(Context context) {
        this.context = context.getApplicationContext();
        prefUtils = new PrefUtils(context);

        try {
            String pluginPath = prefUtils.getPluginPath();
            String pluginPkg = prefUtils.getPluginPkg();
            mSuffix = prefUtils.getSuffix();

            File file = new File(pluginPath);
            if (file.exists()) {
                loadPlugin(pluginPath, pluginPkg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            prefUtils.clear();
        }
    }

    public ResourceManager getResourceManager() {
        if (!usePlugin()) {
            return new ResourceManager(context.getResources(), context.getPackageName(), mSuffix);
        }
        return resourceManager;
    }

    private void loadPlugin(String skinPluginPath, String skinPluginPkg) {
        try {

            if (skinPluginPath.equals(mCurPath) && skinPluginPkg.equals(mCurPkg)) {
                return;
            }

            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPathMethod = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPathMethod.invoke(assetManager, skinPluginPath);

            Resources superResource = context.getResources();

            Resources resources = new Resources(assetManager, superResource.getDisplayMetrics(), superResource.getConfiguration());
            resourceManager = new ResourceManager(resources, skinPluginPkg, null);

            mCurPath = skinPluginPath;
            mCurPkg = skinPluginPkg;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public List<SkinView> getSkinViews(ISkinChangedListener listener) {
        return mSkinViewMaps.get(listener);
    }

    public void addSkinView(ISkinChangedListener listener, List<SkinView> views) {
        mSkinViewMaps.put(listener, views);
    }

    public void registerListener(ISkinChangedListener listener) {
        mListeners.add(listener);
    }

    public void unRegisterListener(ISkinChangedListener listener) {
        mListeners.remove(listener);
        mSkinViewMaps.remove(listener);
    }

    public void changeSkin(String suffix) {
        clearPluginInfo();
        mSuffix = suffix;
        prefUtils.saveSuffix(suffix);
        notifyChangedListener();
    }

    private void clearPluginInfo() {
        mCurPath = null;
        mCurPkg = null;
        mSuffix = null;
        prefUtils.clear();
    }

    @SuppressLint("StaticFieldLeak")
    public void changeSkin(final String skinPluginPath, final String skinPluginPkg, ISkinChangeCallBack callBack) {
        if (callBack == null) {
            callBack = ISkinChangeCallBack.DEFAULT_CALLBACK;
        }

        final ISkinChangeCallBack finalCallback = callBack;

        finalCallback.onStart();

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                try {
                    loadPlugin(skinPluginPath, skinPluginPkg);
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
                return 0;
            }

            @Override
            protected void onPostExecute(Integer code) {
                if (code == -1) {
                    finalCallback.onError(null);
                    return;
                }
                try {
                    notifyChangedListener();
                    finalCallback.onComplete();

                    upDatePluginInfo(skinPluginPath, skinPluginPkg);

                } catch (Exception e) {
                    e.printStackTrace();
                    finalCallback.onError(e);
                }
            }
        }.execute();

    }

    private void upDatePluginInfo(String skinPluginPath, String skinPluginPkg) {
        prefUtils.savePluginPath(skinPluginPath);
        prefUtils.savePluginPkg(skinPluginPkg);
    }


    private void notifyChangedListener() {

        for (ISkinChangedListener listener : mListeners) {
            skinChanged(listener);
            listener.onSkinChanged();
        }

    }

    public void skinChanged(ISkinChangedListener listener) {
        List<SkinView> skinViews = mSkinViewMaps.get(listener);
        for (SkinView skinView : skinViews) {
            skinView.apply();
        }
    }

    public boolean needChangeSkin() {
        return usePlugin() || useSuffix();
    }

    private boolean usePlugin() {
        return mCurPath != null && !mCurPath.trim().equals("");
    }

    private boolean useSuffix() {
        return mSuffix != null && !mSuffix.trim().equals("");
    }

}
