package com.nimtego.itunes.presentation.main.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.adapter.AlbumAdapter;
import com.nimtego.itunes.presentation.main.adapter.ArtistAdapter;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

public class ArtistTabsFragment extends MainTabsFragment {
    @Override
    public void setSearchList(MainDataModel dataModel) {
        RecyclerView.Adapter adapter = new ArtistAdapter(dataModel.getArtistModels(),
                this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }
}
