package com.lanltn.android_base_mvp.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lanltn.android_base_mvp.base.listener.IBaseContainerFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private ParentPresenter mPresenter;
    public IBaseContainerFragment mFragmentListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mPresenter != null) {
            mPresenter.onDestroyed();
        }
    }

    protected abstract ParentPresenter initPresenter();

    protected abstract int getLayoutResourceId();

    public void setFragmentListener(IBaseContainerFragment mFragmentListener) {
        this.mFragmentListener = mFragmentListener;
    }
}
