package com.lanltn.homestay.modules.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.lanltn.android_base_mvp.base.BaseActivity;
import com.lanltn.android_base_mvp.base.ParentPresenter;
import com.lanltn.homestay.R;
import com.lanltn.homestay.modules.base.ParentContainerFragment;
import com.lanltn.homestay.modules.main.adapter.ViewPagerAdapter;
import com.lanltn.homestay.modules.tabbar.CustomTab;
import com.lanltn.homestay.widget.LockableViewPager;

import butterknife.BindView;

import static com.lanltn.homestay.modules.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_0;
import static com.lanltn.homestay.modules.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_1;
import static com.lanltn.homestay.modules.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_2;
import static com.lanltn.homestay.modules.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_3;
import static com.lanltn.homestay.modules.base.ParentContainerFragment.ParentContainerRoot.PARENT_TAB_4;

public class MainActivity
        extends BaseActivity implements MainContract.MainView {
    private static final long DELAY_INIT_VIEWPAGER = 10;

    MainPresenter mMainPresenter;
    @BindView(R.id.viewPager)
    LockableViewPager mViewPager;
    @BindView(R.id.tabs)
    CustomTab mCustomTab;
    @BindView(R.id.v_line)
    View mLine;

    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected ParentPresenter initPresenter() {
        return mMainPresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        init();
    }

//    @Override
//    public void initView() {
//        init();
//    }

    private void init() {
        initViewPager();
    }

    private void initViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter
                .addFrag(ParentContainerFragment.newInstance(PARENT_TAB_0), getString(R.string.main_tab_discover));
        mViewPagerAdapter
                .addFrag(ParentContainerFragment
                        .newInstance(PARENT_TAB_1), getString(R.string.main_tab_map));
        mViewPagerAdapter
                .addFrag(ParentContainerFragment.newInstance(PARENT_TAB_2), getString(R.string.main_tab_favorite));
        mViewPagerAdapter
                .addFrag(ParentContainerFragment
                        .newInstance(PARENT_TAB_3), getString(R.string.main_tab_notification));
        mViewPagerAdapter
                .addFrag(ParentContainerFragment.newInstance(PARENT_TAB_4), getString(R.string.main_tab_profile));
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setSwipeLocked(true);
        mViewPager.setOffscreenPageLimit(mViewPagerAdapter.getCount());
        mCustomTab.setupWithViewPager(mViewPager);
        mCustomTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(final TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void initView() {

    }
}
