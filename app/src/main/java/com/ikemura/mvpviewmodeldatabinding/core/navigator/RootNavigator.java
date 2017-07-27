package com.ikemura.mvpviewmodeldatabinding.core.navigator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ikemura.mvpviewmodeldatabinding.Constants;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.LifecycleCollection;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.SimpleLifecycle;
import com.ikemura.mvpviewmodeldatabinding.common.navigation.NavigationBarViewModel;
import com.ikemura.mvpviewmodeldatabinding.core.BasePresenter;
import com.ikemura.mvpviewmodeldatabinding.core.Navigator;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;

import java.util.ArrayList;
import java.util.List;

public class RootNavigator extends SimpleLifecycle implements BasePresenter, Navigator {
    public static final String TAG = "RootNavigator";

    private final DebugLogger mLogger = new DebugLogger(RootNavigator.class);

    private List<Navigator> mNavigators;

    private NavigationBarViewModel mNavigationBarViewModel = new NavigationBarViewModel();

    public RootNavigator(LifecycleCollection lifecycleCollection) {
        super(lifecycleCollection, null);
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        mNavigators = new ArrayList<>();
    }

    public void pushNavigator(Navigator navigator) {
        mNavigators.add(0, navigator);
    }

    public void popNavigator() {
        mNavigators.remove(0);
    }

    @Override
    public boolean back() {
        for (Navigator navigator : mNavigators) {
            if (navigator.back()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean push(Fragment fragment, String tag) {
        for (Navigator navigator : mNavigators) {
            if (navigator.push(fragment, tag)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean openModal(Fragment fragment, String tag) {
        for (Navigator navigator : mNavigators) {
            if (navigator.openModal(fragment, tag)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean closeModal() {
        for (Navigator navigator : mNavigators) {
            if (navigator.closeModal()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean navigateTo(ScreenName screenName, Bundle args) {
        for (Navigator navigator : mNavigators) {
            if (navigator.navigateTo(screenName, args)) {
                return true;
            }
        }
        return false;
    }

    public void navigateTo(ScreenName screenName) {
        navigateTo(screenName, null);
    }

    public void navigateTo(ScreenName screenName, int detail_id) {
        Bundle arg = new Bundle();
        arg.putInt(Constants.BUNDLE_KEY_DETAIL_ID, detail_id);
        navigateTo(screenName, arg);
    }

    public NavigationBarViewModel getNavigationBarViewModel() {
        return mNavigationBarViewModel;
    }
}
