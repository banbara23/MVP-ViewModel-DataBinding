package com.ikemura.mvpviewmodeldatabinding.core;

public interface BaseView<T extends BasePresenter> {

    T getPresenter();
}
