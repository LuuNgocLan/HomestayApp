package com.lanltn.android_base_mvp.base;

public interface BasePresenter<V> {
    void attach(V view);

    void detach();
}
