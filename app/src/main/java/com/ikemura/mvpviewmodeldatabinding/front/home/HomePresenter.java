package com.ikemura.mvpviewmodeldatabinding.front.home;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.common.FragmentUtils;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.LifecycleCollection;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.OnCreateListener;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.SimpleLifecycle;
import com.ikemura.mvpviewmodeldatabinding.core.FragmentNavigator;
import com.ikemura.mvpviewmodeldatabinding.core.navigator.RootNavigator;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;
import com.ikemura.mvpviewmodeldatabinding.front.FirstFragment;
import com.ikemura.mvpviewmodeldatabinding.front.FourFragment;
import com.ikemura.mvpviewmodeldatabinding.front.ModalFragment;
import com.ikemura.mvpviewmodeldatabinding.front.SecondFragment;
import com.ikemura.mvpviewmodeldatabinding.front.ThirdFragment;

public class HomePresenter extends SimpleLifecycle implements HomeConstract.Presenter {
    public static final String TAG = HomePresenter.class.getSimpleName();

    private final DebugLogger mLogger = new DebugLogger(HomePresenter.class);

    private FragmentNavigator mFragmentNavigator;

    private RootNavigator mRootNavigator;

    private HomeConstract.View mView;

    enum TabName {
        NoneTab,
        FirstTab,
        SecondTab,
        ThirdTab,
        FourTab
    }

    private TabName mTabName = TabName.NoneTab;

    public HomePresenter(LifecycleCollection lifecycleCollection, OnCreateListener onCreateListener) {
        super(lifecycleCollection, onCreateListener);
    }

    public void onCreate(@Nullable Bundle savedInstanceState, HomeConstract.View view, RootNavigator rootNavigator, FragmentManager fragmentManager) {

        mView = view;
        mRootNavigator = rootNavigator;
        mFragmentNavigator = new FragmentNavigator(fragmentManager);

        getRootNavigator().pushNavigator(this);

        if (mTabName == TabName.NoneTab) {
            getRootNavigator().navigateTo(ScreenName.First);
        }
    }

    public RootNavigator getRootNavigator() {
        return mRootNavigator;
    }

    @Override
    public void onClick(int id) {
        onClickReal(id);
    }

    @Override
    public boolean onNavigationClick(@NonNull MenuItem item) {
        return onClickReal(item.getItemId());
    }

    public boolean onClickReal(int id) {
        for (NavigationItem value : NavigationItem.values()) {
            if (value.mId == id) {
                getRootNavigator().navigateTo(value.mScreenName);
                return true;
            }
        }
        return false;
    }

    private enum NavigationItem {
        First(R.id.nav_bottom_first, ScreenName.First),
        Second(R.id.nav_bottom_second, ScreenName.Second),
        Third(R.id.nav_bottom_third, ScreenName.Third),
        Four(R.id.nav_bottom_four, ScreenName.Four);

        @IdRes
        int mId;
        ScreenName mScreenName;

        NavigationItem(int id, ScreenName screenName) {
            mId = id;
            mScreenName = screenName;
        }
    }

    @Override
    public boolean navigateTo(ScreenName screenName, Bundle args) {
        switch (screenName) {
            case First:
                if (mTabName != TabName.FirstTab) {
                    switchTab(TabName.FirstTab);
                }
                break;
            case Second:
                if (mTabName != TabName.SecondTab) {
                    switchTab(TabName.SecondTab);
                }
                break;
            case Third:
                if (mTabName != TabName.ThirdTab) {
                    switchTab(TabName.ThirdTab);
                }
                break;
            case Four:
                if (mTabName != TabName.FourTab) {
                    switchTab(TabName.FourTab);
                }
                break;
            default:
                return false;
        }

        return HomeNavigateToHelper.navigateTo(getRootNavigator(), screenName, args);
    }

    @Override
    public boolean back() {
        boolean popState = mFragmentNavigator.pop();
        if (!popState) {
            popState = !mView.closeDrawer();
        }
        return popState;
    }

    @Override
    public boolean push(Fragment fragment, String tag) {
        mFragmentNavigator.push(R.id.home_container, fragment, ModalFragment.FRAGMENT_TAG, FragmentUtils.TRANSACTION_ANIMATION_TYPE_PUSH, true);
        return true;
    }

    @Override
    public boolean openModal(Fragment fragment, String tag) {
        return false;
    }

    @Override
    public boolean closeModal() {
        return false;
    }

    private void switchTab(TabName tabName) {
        Fragment fragment = null;

        switch (tabName) {
            case NoneTab:
                return;
            case FirstTab:
                fragment = FirstFragment.newInstance();
                break;
            case SecondTab:
                fragment = SecondFragment.newInstance();
                break;
            case ThirdTab:
                fragment = ThirdFragment.newInstance();
                break;
            case FourTab:
                fragment = FourFragment.newInstance();
                break;
        }

        if (fragment != null) {
            String tag = "home";

            mFragmentNavigator.dumpBackStack();

            mFragmentNavigator.popToTag(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            mFragmentNavigator.replace(R.id.home_container, fragment, tag, FragmentUtils.TRANSACTION_ANIMATION_TYPE_NONE, true);

            mFragmentNavigator.dumpBackStack();

            mTabName = tabName;
        }
    }
}
