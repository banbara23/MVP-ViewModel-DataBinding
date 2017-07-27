package com.ikemura.mvpviewmodeldatabinding.common.lifecycle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class SimpleLifecycle implements Lifecycle {
    final private static String TAG = "SimpleLifecycle";

    final private OnCreateListener mOnCreateListener;

    public SimpleLifecycle(LifecycleCollection lifecycleCollection, OnCreateListener onCreateListener) {
        lifecycleCollection.addLifecycle(this);
        mOnCreateListener = onCreateListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        mOnCreateListener.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
    }
}
