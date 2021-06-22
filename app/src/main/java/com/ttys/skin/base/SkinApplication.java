package com.ttys.skin.base;

import android.app.Application;
import android.content.res.Resources;

import com.ttys.skin.source.SkinSource;

/**
 * tomas modify for Application at 2021 06 10
 */
public class SkinApplication extends Application {
    @Override
    public void onCreate() {
        SkinSource.getInstance().register(this);
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // SkinSource.getInstance().unRegister();//no use
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }
}
