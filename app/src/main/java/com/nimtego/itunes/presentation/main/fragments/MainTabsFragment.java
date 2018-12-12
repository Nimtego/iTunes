package com.nimtego.itunes.presentation.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseFragment;

public abstract class MainTabsFragment<P extends MainTabsContract.Presenter>
        extends BaseFragment<P>
        implements MainTabsContract.View<P> {

    protected static final String RESPONSE = "response_content";

    protected RecyclerView mRecyclerView;
    protected String searchText = "";

    public MainTabsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(RESPONSE)) {
            searchText = getArguments().getString(RESPONSE);
        } else {
            throw new IllegalArgumentException("created through newInstance(...)!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_tabs_fragment, container, false);
        initRV(view, container);
        return view;
    }


    protected void initRV(View view, ViewGroup viewGroup) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(rvLayoutManager(viewGroup.getContext()));
        mRecyclerView.addItemDecoration(itemDecorator());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    protected abstract RecyclerView.ItemDecoration itemDecorator();

    protected abstract RecyclerView.LayoutManager rvLayoutManager(Context context);

    @Override
    public boolean isRvEmpty() {
        if (mRecyclerView.getAdapter() == null)
            return true;
        return mRecyclerView.getAdapter().getItemCount() == 0;
    }

    @Override
    public void clearList() {
        if (mRecyclerView != null)
            mRecyclerView.setAdapter(null);
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
