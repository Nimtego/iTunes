package com.nimtego.itunes.presentation.main.fragments;

import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.adapter.ArtistAdapter;
import com.nimtego.itunes.presentation.main.adapter.SongAdapter;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

public class SongTabsFragment extends MainTabsFragment{
    @Override
    public void setSearchList(MainDataModel dataModel) {
        RecyclerView.Adapter adapter = new SongAdapter(dataModel.getSongModels(),
                this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }
}
