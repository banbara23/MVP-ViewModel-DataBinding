package com.ikemura.mvpviewmodeldatabinding.front.home;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.core.BaseFragment;

public class ChildHomeTabFragment extends BaseFragment {

    protected void initToolBarToggle(Toolbar toolbar) {
        DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.root_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}
