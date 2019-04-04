package com.lanltn.homestay.modules.empty;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lanltn.android_base_mvp.base.BaseFragment;
import com.lanltn.android_base_mvp.base.ParentPresenter;
import com.lanltn.homestay.R;

public class EmptyFragment extends BaseFragment implements EmptyContract.EmptyView {

    private EmptyPresenter mEmptyPresenter;

    @Override
    protected ParentPresenter initPresenter() {
        return mEmptyPresenter = new EmptyPresenter();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_empty;
    }

    public static EmptyFragment newInstance() {
        EmptyFragment fragment = new EmptyFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEmptyPresenter.attach(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEmptyPresenter.detach();
    }
}
