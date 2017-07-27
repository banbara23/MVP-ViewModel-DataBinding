package com.ikemura.mvpviewmodeldatabinding.common.viewpagers;

import android.support.v4.app.Fragment;

public interface PageEntry {

    Fragment create();

    CharSequence getPageTitle();
}
