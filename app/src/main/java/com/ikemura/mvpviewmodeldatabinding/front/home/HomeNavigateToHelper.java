package com.ikemura.mvpviewmodeldatabinding.front.home;

import android.os.Bundle;

import com.ikemura.mvpviewmodeldatabinding.core.Navigator;
import com.ikemura.mvpviewmodeldatabinding.core.navigator.RootNavigator;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;
import com.ikemura.mvpviewmodeldatabinding.front.DetailFragment;
import com.ikemura.mvpviewmodeldatabinding.front.ModalFragment;

public class HomeNavigateToHelper {
    public static final String TAG = "HomeNavigateToHelper";

    private final DebugLogger mLogger = new DebugLogger(RootNavigator.class);

    static public boolean navigateTo(RootNavigator rootNavigator, Navigator.ScreenName screenName, Bundle args) {

        switch (screenName) {
            case Modal: {
                ModalFragment fragment = ModalFragment.newInstance();
                rootNavigator.openModal(fragment, ModalFragment.FRAGMENT_TAG);
                break;
            }
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
