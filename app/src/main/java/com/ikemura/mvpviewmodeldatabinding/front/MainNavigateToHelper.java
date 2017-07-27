package com.ikemura.mvpviewmodeldatabinding.front;

import android.os.Bundle;
import android.util.Log;

import com.ikemura.mvpviewmodeldatabinding.core.Navigator;
import com.ikemura.mvpviewmodeldatabinding.core.navigator.RootNavigator;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;

public class MainNavigateToHelper {
    public static final String TAG = "MainNavigateToHelper";

    private final DebugLogger mLogger = new DebugLogger(RootNavigator.class);

    static public boolean navigateTo(RootNavigator rootNavigator, Navigator.ScreenName screenName, Bundle args) {
        Log.d(TAG, "call navigateTo " + screenName + " args " + args);

        switch (screenName) {
            case Detail: {
                DetailFragment fragment = DetailFragment.newInstance();
                rootNavigator.push(fragment, DetailFragment.FRAGMENT_TAG);
                break;
            }
            default:
                return false;
        }

        return true;
    }
}
