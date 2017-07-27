package com.ikemura.mvpviewmodeldatabinding.core;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ikemura.mvpviewmodeldatabinding.common.FragmentUtils;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;

import java.io.PrintWriter;

public class FragmentNavigator {
    public static final String TAG = "FragmentNavigator";

    private final DebugLogger mLogger = new DebugLogger(FragmentNavigator.class);

    final private FragmentManager mFragmentManager;

    public FragmentNavigator(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void replace(@IdRes int container_id, @NonNull Fragment fragment, String tag, int transitionType, boolean addBackStack) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        FragmentUtils.setPredefinedTransitionAnimation(ft, transitionType);
        ft.replace(container_id, fragment, tag);

        if (addBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();

        dumpBackStack();
    }

    public void push(@IdRes int container_id, @NonNull Fragment fragment, String tag, int transitionType, boolean addBackStack) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        FragmentUtils.setPredefinedTransitionAnimation(ft, transitionType);
        ft.add(container_id, fragment, tag);

        if (addBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();

        dumpBackStack();
    }

    private FragmentManager.BackStackEntry getTopEntry() {
        int count = mFragmentManager.getBackStackEntryCount();

        if (count > 0) {
            return mFragmentManager.getBackStackEntryAt(count - 1);
        }
        return null;
    }

    public int getTopId() {
        FragmentManager.BackStackEntry entry = getTopEntry();

        return entry != null ? entry.getId() : 0;
    }

    public String getTopTag() {
        FragmentManager.BackStackEntry entry = getTopEntry();

        return entry != null ? entry.getName() : null;
    }

    public void popToId(int id, int flags) {
        mFragmentManager.popBackStack(id, flags);
        dumpBackStack();
    }

    public void popToTag(String tag, int flags) {
        mFragmentManager.popBackStack(tag, flags);
        dumpBackStack();
    }

    public void dumpBackStack() {
        mLogger.d(TAG, "############");
        mFragmentManager.dump("", null, new PrintWriter(System.out, true), null);

        int count = mFragmentManager.getBackStackEntryCount();
        mLogger.d(TAG, "count = " + count);
        for (int i = 0; i < count; i++) {
            FragmentManager.BackStackEntry entry = mFragmentManager.getBackStackEntryAt(i);

            mLogger.d(TAG, "[" + i + "] id = " + entry.getId());
            mLogger.d(TAG, "[" + i + "] name = " + entry.getName());
            mLogger.d(TAG, "[" + i + "] breadCrumbTitle = " + entry.getBreadCrumbTitle());
            mLogger.d(TAG, "[" + i + "] breadCrumbShortTitle = " + entry.getBreadCrumbShortTitle());
        }
        mLogger.d(TAG, "############");
    }

    /**
     * 戻る
     *
     * @return true:戻れた, false:バックスタックゼロ
     */
    public boolean pop() {
        int count = mFragmentManager.getBackStackEntryCount();

        if (count > 1) {
            FragmentManager.BackStackEntry entry = mFragmentManager.getBackStackEntryAt(count - 1);
            Fragment fragment = mFragmentManager.findFragmentByTag(entry.getName());

            if (fragment instanceof BaseFragment) {
                ((BaseFragment) fragment).popBackStack();
            } else {
                mFragmentManager.popBackStack();
            }

            return true;
        }

        return false;
    }
}
