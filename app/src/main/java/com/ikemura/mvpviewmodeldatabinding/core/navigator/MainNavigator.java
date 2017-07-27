package com.ikemura.mvpviewmodeldatabinding.core.navigator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.common.FragmentUtils;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.LifecycleCollection;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.OnCreateListener;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.SimpleLifecycle;
import com.ikemura.mvpviewmodeldatabinding.core.FragmentNavigator;
import com.ikemura.mvpviewmodeldatabinding.core.Navigator;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;
import com.ikemura.mvpviewmodeldatabinding.front.MainNavigateToHelper;
import com.ikemura.mvpviewmodeldatabinding.front.home.HomeFragment;

public class MainNavigator extends SimpleLifecycle implements Navigator {
    public static final String TAG = "MainNavigator";

    private final DebugLogger mLogger = new DebugLogger(RootNavigator.class);

    private RootNavigator mRootNavigator;

    private FragmentNavigator mFragmentNavigator;

    private Navigator mModalNavigator;

    public MainNavigator(LifecycleCollection lifecycleCollection, OnCreateListener onCreateListener) {
        super(lifecycleCollection, onCreateListener);
    }

    public void onCreate(@Nullable Bundle savedInstanceState, RootNavigator rootNavigator, FragmentManager fragmentManager) {

        mRootNavigator = rootNavigator;
        mFragmentNavigator = new FragmentNavigator(fragmentManager);

        getRootNavigator().pushNavigator(this);

        mModalNavigator = new ModalNavigator(getRootNavigator(), mFragmentNavigator);

        replaceHomeFragment();
    }

    public RootNavigator getRootNavigator() {
        return mRootNavigator;
    }

    @Override
    public boolean navigateTo(Navigator.ScreenName screenName, Bundle args) {
        return MainNavigateToHelper.navigateTo(getRootNavigator(), screenName, args);
    }

    @Override
    public boolean back() {
        return mFragmentNavigator.pop();
    }

    @Override
    public boolean push(Fragment fragment, String tag) {
        mFragmentNavigator.push(R.id.root_container, fragment, tag, FragmentUtils.TRANSACTION_ANIMATION_TYPE_PUSH, true);
        return true;
    }

    @Override
    public boolean openModal(Fragment fragment, String tag) {
        getRootNavigator().pushNavigator(mModalNavigator);
        return mModalNavigator.openModal(fragment, tag);
    }

    @Override
    public boolean closeModal() {
        return false;
    }

    private void replaceHomeFragment() {
        HomeFragment fragment = HomeFragment.newInstance();
        mFragmentNavigator.replace(R.id.root_container, fragment, HomeFragment.TAG, FragmentUtils.TRANSACTION_ANIMATION_TYPE_NONE, false);
    }
}
