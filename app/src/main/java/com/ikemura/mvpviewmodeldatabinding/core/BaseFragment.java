package com.ikemura.mvpviewmodeldatabinding.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.Lifecycle;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.LifecycleCollection;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.SimpleLifecycleCollection;
import com.ikemura.mvpviewmodeldatabinding.core.navigator.RootNavigator;
import com.ikemura.mvpviewmodeldatabinding.front.MainActivity;

public class BaseFragment extends Fragment implements LifecycleCollection {

    LifecycleCollection mLifecycleCollection = new SimpleLifecycleCollection();

    public RootNavigator getRootNavigator() {
        return ((MainActivity) getActivity()).getRootNavigator();
    }

    public void popBackStack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleCollection.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mLifecycleCollection.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mLifecycleCollection.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mLifecycleCollection.onResume();
    }

    @Override
    public void onPause() {
        mLifecycleCollection.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        mLifecycleCollection.onStop();
        super.onStop();
    }

    @Override
    public void addLifecycle(Lifecycle lifecycle) {
        mLifecycleCollection.addLifecycle(lifecycle);
    }

    @Override
    public void removeLifecycle(Lifecycle lifecycle) {
        mLifecycleCollection.removeLifecycle(lifecycle);
    }
}
