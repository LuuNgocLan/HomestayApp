package com.lanltn.android_base_mvp.base;

import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;

import com.lanltn.android_base_mvp.base.listener.IBaseContainerFragment;

import java.util.List;

public abstract class BaseContainerFragment extends BaseFragment implements IBaseContainerFragment {

    @Override
    public void addChildFragment(Fragment fragment, boolean addToBackStack, boolean animate) {
        if (getContext() == null) {
            return;
        }
        if (fragment instanceof BaseFragment) {
            ((BaseFragment) fragment).setFragmentListener(this);
        }

        if (animate) {
            fragment.setEnterTransition(new Slide(Gravity.RIGHT));
            fragment.setExitTransition(new Slide(Gravity.LEFT));
        }

        try {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getTag());
            }
            fragmentTransaction.add(getResLayoutFrameId(), fragment);
            fragmentTransaction.commit();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

//        removeLimitedFragment();
    }

    /**
     * Prevent too much backstack stored in a Tab
     */
    private void removeLimitedFragment() {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments.size() > 3) {
            Fragment fragment = fragments.get(1);
            fragment.setExitTransition(new Slide(Gravity.END));
            getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
        }
    }

    @Override
    public boolean popToRoot() {
        if (getContext() == null) {
            return true;
        }
        boolean isRoot = true;
        int size = getChildFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < size; i++) {
            isRoot = false;
            getChildFragmentManager().popBackStackImmediate();
        }
        return isRoot;
    }

    public void popAllFragments() {
        if (getContext() == null) {
            return;
        }
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    public int getSize() {
        return getChildFragmentManager().getFragments().size();
    }

    public boolean allowBackForMain() {
        if (getContext() == null) {
            return true;
        }
        int size = getChildFragmentManager().getBackStackEntryCount();
        getChildFragmentManager().popBackStack();
        return size == 0;
    }

    @Override
    public Fragment getCurrentFragment() {
        if (getContext() == null) {
            return null;
        }
        return getChildFragmentManager().findFragmentById(getResLayoutFrameId());
    }

    public abstract int getResLayoutFrameId();

}
