package com.nimtego.plectrum.presentation.main.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nimtego.plectrum.presentation.main.MainContract;
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private final List<MainTabsFragment> mFragmentList;
    private final List<String> mFragmentTitleList;

    private MainContract.View parent;

    public MainPagerAdapter(MainContract.View parent, FragmentManager manager) {
        super(manager);
        mFragmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
        this.parent = parent;
    }

    @Override
    public MainTabsFragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(MainTabsFragment fragment, String title) {
        fragment.onAttach((Context) parent);
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

}
