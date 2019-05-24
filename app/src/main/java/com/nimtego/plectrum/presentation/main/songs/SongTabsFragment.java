package com.nimtego.plectrum.presentation.main.songs;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.plectrum.App;
import com.nimtego.plectrum.presentation.main.adapter.SpacesItemDecoration;
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragment;
import com.nimtego.plectrum.presentation.main.model.SongModel;

import java.util.ArrayList;
import java.util.Collection;

public class SongTabsFragment
        extends MainTabsFragment<SongContract.Presenter>
        implements SongContract.View<SongContract.Presenter> {


    public static SongTabsFragment getInstance(String response) {
        final SongTabsFragment fragment = new SongTabsFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(RESPONSE, response);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected RecyclerView.ItemDecoration itemDecorator() {
        return new SpacesItemDecoration(1,
                20,
                true);
    }

    @Override
    protected RecyclerView.LayoutManager rvLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Override
    public void render(Collection<SongModel> songModels) {
        SongAdapter adapter = new SongAdapter(new ArrayList<>(songModels),
                this.getActivity());
        adapter.setOnItemClickListener(artistModel -> mPresenter.songClicked(artistModel));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void search(String response) {
        mPresenter.search(response);
    }

    @Override
    public SongContract.Presenter supplyPresenter() {
        return App.getComponent().mainSongPresenter();
    }
}
