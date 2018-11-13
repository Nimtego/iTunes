package com.nimtego.itunes.presentation.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BaseFragment;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

import java.util.Collection;

public abstract class MainTabsFragment<P extends MainTabsContract.Presenter>
extends BaseFragment<P>
        implements MainTabsContract.View<P>{

    protected RecyclerView mRecyclerView;
    protected String searchText = "";
    public MainTabsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_tabs_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(rvLayoutManager(container.getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    protected abstract RecyclerView.LayoutManager rvLayoutManager(Context context);

    @Override
    public boolean isRvEmpty() {
        if ( mRecyclerView.getAdapter() == null)
            return true;
        return mRecyclerView.getAdapter().getItemCount() == 0;
    }

    @Override
    public String getCurrentSerch() {
        return searchText;
    }

    @Override
    public void setCurrentSearch(String response) {
        this.searchText = response;
    }
}
