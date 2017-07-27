package com.ikemura.mvpviewmodeldatabinding;

import android.app.Application;

import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DebugLogger.setDebugMode(true);
    }
}
