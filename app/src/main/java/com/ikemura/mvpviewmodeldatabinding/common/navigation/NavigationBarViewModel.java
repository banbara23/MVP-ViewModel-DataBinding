package com.ikemura.mvpviewmodeldatabinding.common.navigation;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ikemura.mvpviewmodeldatabinding.BR;

public class NavigationBarViewModel extends BaseObservable {

    private String mTitle;

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
        notifyPropertyChanged(BR.title);
    }
}


