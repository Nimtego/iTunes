package com.nimtego.itunes.presentation.main.fragments;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.ArtistContract;
import com.nimtego.itunes.presentation.main.ArtistPresenter;
import com.nimtego.itunes.presentation.main.adapter.ArtistAdapter;
import com.nimtego.itunes.presentation.main.model.ArtistModel;

import java.util.ArrayList;
import java.util.Collection;

public class ArtistTabsFragment
        extends MainTabsFragment<ArtistContract.Presenter>
        implements ArtistContract.View<ArtistContract.Presenter> {

    @Override
    protected RecyclerView.LayoutManager rvLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    @Override
    public void render(Collection<ArtistModel> artistModels) {
        RecyclerView.Adapter adapter = new ArtistAdapter(new ArrayList<>(artistModels),
                this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void search(String response) {
        mPresenter.search(response);
    }

    @Override
    public ArtistContract.Presenter supplyPresenter() {
        return new ArtistPresenter();
    }
}
