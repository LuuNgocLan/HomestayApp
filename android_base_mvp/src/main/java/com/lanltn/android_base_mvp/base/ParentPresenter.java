package com.lanltn.android_base_mvp.base;

import com.lanltn.android_base_mvp.base.listener.INetworkListener;
import com.lanltn.android_core_helper.network.ServiceGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ParentPresenter<T> {

    private T mWsInterface;
    private List<Disposable> disposables = new ArrayList<>();

    /**
     * Using for view model with api methods
     *
     * @param apiClass class of api define (follow retrofit interface)
     * @param baseUrl  Base url using for all api call in this class
     */
    protected ParentPresenter(Class<T> apiClass, String baseUrl) {
        mWsInterface = ServiceGenerator.createService(apiClass, baseUrl);
    }

    /**
     * Using for view model with api methods
     *
     * @param apiClass class of api define (follow retrofit interface)
     * @param baseUrl  Base url using for all api call in this class
     */
    protected ParentPresenter(Class<T> apiClass, String baseUrl, HashMap<String, String> headers) {
        mWsInterface = ServiceGenerator.createService(apiClass, baseUrl, headers);
    }

    /**
     * Using when created view model with no api methods.
     */
    protected ParentPresenter() {

    }

    protected T getApiService() {
        return mWsInterface;
    }

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void onDestroyed() {
        for (Disposable d : disposables) {
            if (d != null && !d.isDisposed()) {
                d.dispose();
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void callApi(Observable observable, final INetworkListener iNetworkListener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(Object data) {
                        iNetworkListener.onFinished(data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iNetworkListener.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
