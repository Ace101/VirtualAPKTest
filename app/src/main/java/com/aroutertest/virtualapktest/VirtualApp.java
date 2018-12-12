package com.aroutertest.virtualapktest;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * @author zhangshihao
 * @date 2018/12/7 0007
 */
public class VirtualApp extends Application {

    private boolean isDebugARouter = true;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
