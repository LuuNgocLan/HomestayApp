package com.lanltn.android_base_mvp.base.listener;

import android.support.v4.app.Fragment;

public interface IBaseContainerFragment {

    void addChildFragment(Fragment fragment, boolean addToBackStack, boolean animate);

    Fragment getCurrentFragment();

    boolean popToRoot();
}
