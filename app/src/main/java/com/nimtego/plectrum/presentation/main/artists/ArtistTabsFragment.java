package com.nimtego.plectrum.presentation.main.artists;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.plectrum.App;
import com.nimtego.plectrum.presentation.main.adapter.SpacesItemDecoration;
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragment;
import com.nimtego.plectrum.presentation.main.model.ArtistModel;

import java.util.ArrayList;
import java.util.Collection;

@Deprecated
public class ArtistTabsFragment
        extends MainTabsFragment<ArtistContract.Presenter>
        implements ArtistContract.View<ArtistContract.Presenter> {

    public static ArtistTabsFragment getInstance(String response) {
        final ArtistTabsFragment fragment = new ArtistTabsFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(RESPONSE, response);
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    protected RecyclerView.ItemDecoration itemDecorator() {
        return new SpacesItemDecoration(2,
                30,
                true);
    }

    @Override
    protected RecyclerView.LayoutManager rvLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    @Override
    public void render(Collection<ArtistModel> artistModels) {
        ArtistAdapter adapter = new ArtistAdapter(new ArrayList<>(artistModels),
                this.getActivity());
        adapter.setOnItemClickListener(new ArtistAdapter.OnItemClickListener() {
            @Override
            public void onUserItemClicked(ArtistModel artistModel) {
                mPresenter.artistClicked(artistModel);
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void search(String response) {
        mPresenter.search(response);
    }

    @Override
    public ArtistContract.Presenter supplyPresenter() {
        return App.getComponent().mainArtistPresenter();
    }
}
