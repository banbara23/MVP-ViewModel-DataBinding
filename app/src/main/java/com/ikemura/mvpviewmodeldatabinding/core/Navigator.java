package com.ikemura.mvpviewmodeldatabinding.core;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public interface Navigator {

    boolean navigateTo(ScreenName screenName, Bundle args);

    boolean back();

    boolean push(Fragment fragment, String tag);

    boolean openModal(Fragment fragment, String tag);

    boolean closeModal();

    enum ScreenName {
        First,
        Second,
        Third,
        Four,
        Modal,
        Detail,
    }
}
