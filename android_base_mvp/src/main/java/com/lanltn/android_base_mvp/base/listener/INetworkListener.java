package com.lanltn.android_base_mvp.base.listener;

public interface INetworkListener<T extends Object>
{
    public void onFinished(T data);

    public void onError(Throwable throwable);

}
