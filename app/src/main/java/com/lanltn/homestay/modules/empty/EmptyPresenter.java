package com.lanltn.homestay.modules.empty;

import com.lanltn.android_base_mvp.base.ParentPresenter;

public class EmptyPresenter extends ParentPresenter implements EmptyContract.EmptyPresenter {
    private EmptyContract.EmptyView mView;

    public EmptyPresenter(){
//        super(WSInterface.class, Constant.baseUrl);
    }

    @Override
    public void attach(EmptyContract.EmptyView view) {
        this.mView = view;
    }

    @Override
    public void detach() {
        this.mView = null;
    }
}
