package com.lanltn.homestay.modules.main;

import com.lanltn.android_base_mvp.base.BasePresenter;
import com.lanltn.android_base_mvp.base.BaseView;

public class MainContract {
    interface MainView extends BaseView {

        void initView();
    }

    interface MainPresenter extends BasePresenter<MainView> {

    }
}
