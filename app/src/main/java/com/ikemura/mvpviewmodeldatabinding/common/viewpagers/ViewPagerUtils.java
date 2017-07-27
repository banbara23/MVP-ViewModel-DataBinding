package com.ikemura.mvpviewmodeldatabinding.common.viewpagers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class ViewPagerUtils {
    private static final String BUNDLE_KEY_PAGE_INDEX = "pageIndex";

    public static void setPageIndex(Fragment fragment, int pageIndex) {
        Bundle arguments = fragment.getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putInt(BUNDLE_KEY_PAGE_INDEX, pageIndex);
        fragment.setArguments(arguments);
    }

    public static int getPageIndex(Fragment fragment, int defaultPageIndex) {
        Bundle arguments = fragment.getArguments();
        if (arguments != null) {
            return arguments.getInt(BUNDLE_KEY_PAGE_INDEX, defaultPageIndex);
        }
        return defaultPageIndex;
    }
}
