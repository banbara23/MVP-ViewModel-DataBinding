package com.ikemura.mvpviewmodeldatabinding.common.lifecycle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SimpleLifecycleCollection implements LifecycleCollection {

    private List<Lifecycle> mLifecycles = new ArrayList<>();

    @Override
    public void addLifecycle(Lifecycle presenter) {
        mLifecycles.add(presenter);
    }

    @Override
    public void removeLifecycle(Lifecycle presenter) {
        mLifecycles.remove(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        for (Lifecycle lifecycle : mLifecycles) {
            lifecycle.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        for (Lifecycle lifecycle : mLifecycles) {
            lifecycle.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onStart() {
        for (Lifecycle lifecycle : mLifecycles) {
            lifecycle.onStart();
        }
    }

    @Override
    public void onResume() {
        for (Lifecycle lifecycle : mLifecycles) {
            lifecycle.onResume();
        }
    }

    @Override
    public void onPause() {
        for (ListIterator<Lifecycle> iterator = mLifecycles.listIterator(mLifecycles.size()); iterator.hasPrevious(); ) {
            iterator.previous().onPause();
        }
    }

    @Override
    public void onStop() {
        for (ListIterator<Lifecycle> iterator = mLifecycles.listIterator(mLifecycles.size()); iterator.hasPrevious(); ) {
            iterator.previous().onStop();
        }
    }

}
