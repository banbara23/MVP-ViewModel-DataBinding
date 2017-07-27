package com.ikemura.mvpviewmodeldatabinding.common.lifecycle;

public interface LifecycleCollection extends Lifecycle {

    void addLifecycle(Lifecycle presenter);

    void removeLifecycle(Lifecycle presenter);
}
