package com.ikemura.mvpviewmodeldatabinding.core;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;

@BindingMethods({
        @BindingMethod(
                type = BottomNavigationView.class,
                attribute = "onNavigationItemSelected",
                method = "setOnNavigationItemSelectedListener"
        ),
        @BindingMethod(
                type = NavigationView.class,
                attribute = "onNavigationItemSelected",
                method = "setOnNavigationItemSelectedListener"
        ),
        @BindingMethod(
                type = Toolbar.class,
                attribute = "setNavigationOnClickListener",
                method = "setNavigationOnClickListener"
        ),
})
public class DataBindingAdapter {
}
