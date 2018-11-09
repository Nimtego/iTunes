package com.nimtego.itunes.presentation.main.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.adapter.SongAdapter;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

public class SongTabsFragment extends MainTabsFragment {

    @Override
    protected RecyclerView.LayoutManager rvLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Override
    public void setSearchList(MainDataModel dataModel) {
        RecyclerView.Adapter adapter = new SongAdapter(dataModel.getSongModels(),
                this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }
}
