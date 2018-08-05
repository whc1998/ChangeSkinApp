package com.example.whc.changeshin.Skin;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.whc.changeshin.Skin.Config.Const;

/**
 * Created by WSY on 2018/6/9.
 */

public class PrefUtils {

    private Context context;

    public PrefUtils(Context context) {
        this.context = context;
    }

    public void savePluginPath(String path) {
        SharedPreferences sp = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(Const.KEY_PLUGIN_PATH, path).apply();
    }

    public String getPluginPath() {
        SharedPreferences sp = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.KEY_PLUGIN_PATH, "");
    }

    public void savePluginPkg(String pkg) {
        SharedPreferences sp = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(Const.KEY_PLUGIN_PKG, pkg).apply();
    }

    public String getPluginPkg() {
        SharedPreferences sp = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.KEY_PLUGIN_PKG, "");
    }

    public void saveSuffix(String suffix) {
        SharedPreferences sp = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(Const.KEY_SUFFIX, suffix).apply();
    }

    public String getSuffix() {
        SharedPreferences sp = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        return sp.getString(Const.KEY_SUFFIX, "");
    }

    public void clear() {
        SharedPreferences sp = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }

}
