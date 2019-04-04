package com.lanltn.homestay.modules.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.lanltn.android_base_mvp.base.BaseContainerFragment;
import com.lanltn.android_base_mvp.base.ParentPresenter;
import com.lanltn.homestay.R;
import com.lanltn.homestay.modules.empty.EmptyFragment;

public class ParentContainerFragment
        extends BaseContainerFragment {

    private static final String ARGUMENT_PARENT_ROOT = "ARGUMENT_PARENT_ROOT";

    private ParentContainerRoot mRoot;

    public static ParentContainerFragment newInstance(ParentContainerRoot root) {
        ParentContainerFragment fragment = new ParentContainerFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGUMENT_PARENT_ROOT, root);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRoot = (ParentContainerRoot) getArguments().get(ARGUMENT_PARENT_ROOT);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    protected ParentPresenter initPresenter() {
        return null;
    }

    void init() {
        if (mRoot == null) {
            return;
        }
        switch (mRoot) {
            case PARENT_TAB_0:
                addChildFragment(EmptyFragment.newInstance(), false, false);
                break;
            case PARENT_TAB_1:
                addChildFragment(EmptyFragment.newInstance(), false, false);
                break;
            case PARENT_TAB_2:
                addChildFragment(EmptyFragment.newInstance(), false, false);
                break;
            case PARENT_TAB_3:
                addChildFragment(EmptyFragment.newInstance(), false, false);
//                addChildFragment(MapFragment.newInstance(), false, false);
                break;
            case PARENT_TAB_4:
                addChildFragment(EmptyFragment.newInstance(), false, false);
                break;

        }
    }

    public void scrollTopRootScreen() {
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.layout_parent_container;
    }

    @Override
    public int getResLayoutFrameId() {
        return R.id.frame_layout_container;
    }

    public enum ParentContainerRoot {
        PARENT_TAB_0(0),
        PARENT_TAB_1(1),
        PARENT_TAB_2(2),
        PARENT_TAB_3(3),
        PARENT_TAB_4(4);

        ParentContainerRoot(int i) {
            this.value = i;
        }

        private int value;

        public int getValue() {
            return value;
        }
    }
}
