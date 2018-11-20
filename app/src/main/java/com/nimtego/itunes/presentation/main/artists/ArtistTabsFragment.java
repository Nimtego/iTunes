package com.nimtego.itunes.presentation.main.artists;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.model.ArtistModel;

import java.util.ArrayList;
import java.util.Collection;

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
        if (mPresenter == null) {
            mPresenter = supplyPresenter();
            mPresenter.attach(this);
        }
        mPresenter.search(response);
    }

    @Override
    public ArtistContract.Presenter supplyPresenter() {
        return new ArtistPresenter();
    }
}
