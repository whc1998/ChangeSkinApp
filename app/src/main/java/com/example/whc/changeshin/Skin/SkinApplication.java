package com.example.whc.changeshin.Skin;

import android.app.Application;

/**
 * Created by WSY on 2018/6/9.
 */

public class SkinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
