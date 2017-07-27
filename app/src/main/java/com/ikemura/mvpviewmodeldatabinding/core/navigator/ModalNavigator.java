package com.ikemura.mvpviewmodeldatabinding.core.navigator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.common.FragmentUtils;
import com.ikemura.mvpviewmodeldatabinding.core.FragmentNavigator;
import com.ikemura.mvpviewmodeldatabinding.core.Navigator;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;

class ModalNavigator implements Navigator {
    public static final String TAG = "ModalNavigator";

    private final DebugLogger mLogger = new DebugLogger(RootNavigator.class);

    final private FragmentNavigator mFragmentNavigator;

    final private RootNavigator mRootNavigator;

    private String mTopModalTag = null;

    ModalNavigator(RootNavigator rootNavigator, FragmentNavigator fragmentNavigator) {
        mRootNavigator = rootNavigator;
        mFragmentNavigator = fragmentNavigator;
    }

    @Override
    public boolean navigateTo(Navigator.ScreenName screenName, Bundle args) {
        return false;
    }

    public RootNavigator getRootNavigator() {
        return mRootNavigator;
    }

    @Override
    public boolean back() {
        mLogger.d(TAG, "back");
        boolean popState;

        String nowTag = mFragmentNavigator.getTopTag();
        if (mTopModalTag != null && mTopModalTag.equals(nowTag)) {
            popState = closeModal();
        } else {
            popState = mFragmentNavigator.pop();
        }

        mFragmentNavigator.dumpBackStack();
        return popState;
    }

    @Override
    public boolean push(Fragment fragment, String tag) {
        mLogger.d(TAG, "push");
        mFragmentNavigator.dumpBackStack();
        mFragmentNavigator.push(R.id.modal_container, fragment, tag, FragmentUtils.TRANSACTION_ANIMATION_TYPE_PUSH, true);
        mFragmentNavigator.dumpBackStack();

        return true;
    }

    @Override
    public boolean openModal(Fragment fragment, String tag) {
        mLogger.d(TAG, "openModal");
        mFragmentNavigator.dumpBackStack();

        if (mTopModalTag != null) {
            mFragmentNavigator.popToTag(mTopModalTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        mFragmentNavigator.replace(R.id.modal_container, fragment, tag, FragmentUtils.TRANSACTION_ANIMATION_TYPE_MODAL_VERTICAL, true);
        mTopModalTag = tag;

        mFragmentNavigator.dumpBackStack();

        return true;
    }

    @Override
    public boolean closeModal() {
        mLogger.d(TAG, "closeModal");
        mFragmentNavigator.dumpBackStack();

        mFragmentNavigator.popToTag(mTopModalTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getRootNavigator().popNavigator();
        return true;
    }
}
