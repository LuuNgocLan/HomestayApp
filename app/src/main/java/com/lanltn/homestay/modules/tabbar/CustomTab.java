package com.lanltn.homestay.modules.tabbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanltn.homestay.R;

public class CustomTab
        extends TabLayout {
    public static final int TAB_HOME = 0;
    public static final int TAB_LINEUP = 1;
    public static final int TAB_TIMETABLE = 2;
    public static final int TAB_MAP = 3;
    public static final int TAB_SNS = 4;

    private Context mContext;

    public CustomTab(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public CustomTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        if (viewPager.getAdapter() == null) {
            return;
        }
        setupTab();
    }

    private void setupTab() {
//        setTabGravity(TabLayout.GRAVITY_CENTER);

        //this.setSelectedTabIndicatorHeight(0);
        this.setSelectedTabIndicatorColor(mContext.getResources().getColor(android.R.color.transparent));
        this.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.item_tabbar_text))
                        .setTextColor(mContext.getResources().getColor(R.color.main_tab_text_active));
                switch (tab.getPosition()) {
                    case TAB_HOME:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_discover);
                        break;
                    case TAB_LINEUP:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_map);
                        break;
                    case TAB_TIMETABLE:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_favorite);
                        break;
                    case TAB_MAP:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_notification);
                        break;
                    case TAB_SNS:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_profile);
                        break;
                }

            }

            @Override
            public void onTabUnselected(Tab tab) {
                ((TextView) tab.getCustomView().findViewById(R.id.item_tabbar_text))
                        .setTextColor(mContext.getResources().getColor(R.color.main_tab_text_inactive));
                switch (tab.getPosition()) {
                    case 0:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_discover);
                        break;
                    case 1:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_map);
                        break;
                    case 2:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_favorite);
                        break;
                    case 3:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_notification);
                        break;
                    case 4:
                        ((ImageView) tab.getCustomView().findViewById(R.id.item_tabbar_icon))
                                .setImageResource(R.drawable.ic_profile);
                        break;
                }
            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

        RelativeLayout tabHome = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tabbar, null);
        ((TextView) tabHome.findViewById(R.id.item_tabbar_text))
                .setText(mContext.getText(R.string.main_tab_discover));
        ((TextView) tabHome.findViewById(R.id.item_tabbar_text))
                .setTextColor(mContext.getResources().getColor(R.color.main_tab_text_active));
        ((ImageView) tabHome.findViewById(R.id.item_tabbar_icon))
                .setImageResource(R.drawable.ic_discover);
        this.getTabAt(0).setCustomView(tabHome);

        RelativeLayout tabLineup = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tabbar, null);
        ((TextView) tabLineup.findViewById(R.id.item_tabbar_text))
                .setText(mContext.getText(R.string.main_tab_map));
        ((TextView) tabLineup.findViewById(R.id.item_tabbar_text))
                .setTextColor(mContext.getResources().getColor(R.color.main_tab_text_inactive));
        ((ImageView) tabLineup.findViewById(R.id.item_tabbar_icon))
                .setImageResource(R.drawable.ic_map);
        this.getTabAt(1).setCustomView(tabLineup);

        RelativeLayout tabTimeTable = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tabbar, null);
        ((TextView) tabTimeTable.findViewById(R.id.item_tabbar_text))
                .setText(mContext.getText(R.string.main_tab_favorite));
        ((TextView) tabTimeTable.findViewById(R.id.item_tabbar_text))
                .setTextColor(mContext.getResources().getColor(R.color.main_tab_text_inactive));
        ((ImageView) tabTimeTable.findViewById(R.id.item_tabbar_icon))
                .setImageResource(R.drawable.ic_favorite);
        this.getTabAt(2).setCustomView(tabTimeTable);

        RelativeLayout tabMapArea = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tabbar, null);
        ((TextView) tabMapArea.findViewById(R.id.item_tabbar_text))
                .setText(mContext.getText(R.string.main_tab_notification));
        ((TextView) tabMapArea.findViewById(R.id.item_tabbar_text))
                .setTextColor(mContext.getResources().getColor(R.color.main_tab_text_inactive));
        ((ImageView) tabMapArea.findViewById(R.id.item_tabbar_icon))
                .setImageResource(R.drawable.ic_notification);
        this.getTabAt(3).setCustomView(tabMapArea);

        RelativeLayout tabSNS = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tabbar, null);
        ((TextView) tabSNS.findViewById(R.id.item_tabbar_text))
                .setText(mContext.getText(R.string.main_tab_profile));
        ((TextView) tabSNS.findViewById(R.id.item_tabbar_text))
                .setTextColor(mContext.getResources().getColor(R.color.main_tab_text_inactive));
        ((ImageView) tabSNS.findViewById(R.id.item_tabbar_icon))
                .setImageResource(R.drawable.ic_profile);
        this.getTabAt(4).setCustomView(tabSNS);
    }
}
