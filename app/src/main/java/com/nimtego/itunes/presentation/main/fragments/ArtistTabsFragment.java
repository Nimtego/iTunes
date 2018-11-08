package com.nimtego.itunes.presentation.main.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.adapter.AlbumAdapter;
import com.nimtego.itunes.presentation.main.adapter.ArtistAdapter;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

public class ArtistTabsFragment extends MainTabsFragment {

    @Override
    protected RecyclerView.LayoutManager rvLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }
    @Override
    public void setSearchList(MainDataModel dataModel) {
        RecyclerView.Adapter adapter = new ArtistAdapter(dataModel.getArtistModels(),
                this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }
}
