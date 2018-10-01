package com.nimtego.itunes.view.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimtego.itunes.R;
import com.nimtego.itunes.model.ModelManager;
import com.nimtego.itunes.mvp_contracts.TabsFragmentContract;
import com.nimtego.itunes.utils.RecyclerItemClickListener;

public class MainTabsFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected ModelManager mModelManager;

    public MainTabsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_tabs_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container.getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       /* mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                               *//* mPresenter.pushInRV(position);*//*
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                *//*mPresenter.longPushInRV(position);*//*
                            }
                        })
        );*/
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }
}
