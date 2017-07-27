package com.ikemura.mvpviewmodeldatabinding.debug;

import android.util.Log;

import java.util.HashMap;

public class DebugLogger {

    // %%

    private static boolean sDebugMode;
    private static final HashMap<Class<?>, Integer> sInstanceCounter = new HashMap<>();
    private final int mInstanceIndex;

    public static boolean isDebugMode() {
        return sDebugMode;
    }

    public static void setDebugMode(boolean debugMode) {
        sDebugMode = debugMode;
    }

    private final Class<?> mClass;

    public DebugLogger(Class<?> aClass) {
        if (sDebugMode) {
            mClass = aClass;
            Integer count = sInstanceCounter.get(aClass);
            if (count == null) {
                count = 0;
            } else {
                count++;
            }
            sInstanceCounter.put(aClass, count);
            mInstanceIndex = count;
        } else {
            mClass = null;
            mInstanceIndex = 0;
        }
    }

    // %%

    public void w(String tag, String message) {
        if (sDebugMode) {
            Log.w(tag, mClass.getSimpleName() + "[" + mInstanceIndex + "] " + message);
        }
    }

    public void e(String tag, String message) {
        if (sDebugMode) {
            Log.e(tag, mClass.getSimpleName() + "[" + mInstanceIndex + "] " + message);
        }
    }

    public void i(String tag, String message) {
        if (sDebugMode) {
            Log.i(tag, mClass.getSimpleName() + "[" + mInstanceIndex + "] " + message);
        }
    }

    public void v(String tag, String message) {
        if (sDebugMode) {
            Log.v(tag, mClass.getSimpleName() + "[" + mInstanceIndex + "] " + message);
        }
    }

    public void d(String tag, String message) {
        if (sDebugMode) {
            Log.d(tag, mClass.getSimpleName() + "[" + mInstanceIndex + "] " + message);
        }
    }
}
