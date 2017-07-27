package com.ikemura.mvpviewmodeldatabinding.common.viewpagers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class BasicPagerAdapter extends FragmentPagerAdapter {
    private final List<PageEntry> mPageEntries;

    public BasicPagerAdapter(FragmentManager fm, List<PageEntry> pageEntries) {
        super(fm);
        mPageEntries = pageEntries;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mPageEntries.get(position).create();
        ViewPagerUtils.setPageIndex(fragment, position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mPageEntries.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPageEntries.get(position).getPageTitle();
    }
}
