package com.nimtego.itunes.presentation.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimtego.itunes.R;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.presentation.main.adapter.PostAdapter;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.List;

public class MainTabsFragment extends Fragment {

    protected RecyclerView mRecyclerView;

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
        return view;
    }

    public void setSearchList(List<AlbumModel> list) {
        RecyclerView.Adapter adapter = new PostAdapter(list, this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }
}
