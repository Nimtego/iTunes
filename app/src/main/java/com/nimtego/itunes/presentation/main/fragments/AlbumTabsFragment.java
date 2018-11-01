package com.nimtego.itunes.presentation.main.fragments;

import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.adapter.AlbumAdapter;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

public class AlbumTabsFragment extends MainTabsFragment{

    @Override
    public void setSearchList(MainDataModel dataModel) {
        RecyclerView.Adapter adapter = new AlbumAdapter(dataModel.getAlbumModels(),
                                                        this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }
}
