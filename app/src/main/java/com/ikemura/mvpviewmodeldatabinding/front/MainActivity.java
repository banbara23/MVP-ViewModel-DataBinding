package com.ikemura.mvpviewmodeldatabinding.front;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.Lifecycle;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.LifecycleCollection;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.OnCreateListener;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.SimpleLifecycleCollection;
import com.ikemura.mvpviewmodeldatabinding.core.navigator.MainNavigator;
import com.ikemura.mvpviewmodeldatabinding.core.navigator.RootNavigator;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;

public class MainActivity extends AppCompatActivity implements LifecycleCollection {
    public static final String TAG = MainActivity.class.getSimpleName();

    private final DebugLogger mLogger = new DebugLogger(MainActivity.class);

    LifecycleCollection mLifecycleCollection = new SimpleLifecycleCollection();

    private RootNavigator mRootNavigator = new RootNavigator(this);

    private MainNavigator mMainNavigator = new MainNavigator(this, new OnCreateListener() {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            mMainNavigator.onCreate(savedInstanceState, mRootNavigator, getSupportFragmentManager());
        }
    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public RootNavigator getRootNavigator() {
        return mRootNavigator;
    }

    @Override
    public void onBackPressed() {
        getRootNavigator().back();
    }
}