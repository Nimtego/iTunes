package com.nimtego.itunes.presentation.main.albums;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.MainContract;
import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.ArrayList;
import java.util.Collection;

public class AlbumTabsFragment
        extends MainTabsFragment<AlbumContract.Presenter>
        implements AlbumContract.View<AlbumContract.Presenter> {



    public static AlbumTabsFragment getInstance(String response) {
        final AlbumTabsFragment fragment = new AlbumTabsFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(RESPONSE, response);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void render(Collection<AlbumModel> albumModel) {
        AlbumAdapter adapter = new AlbumAdapter(new ArrayList<>(albumModel),
                this.getActivity());
        adapter.setOnItemClickListener(new AlbumAdapter.OnItemClickListener() {
            @Override
            public void onUserItemClicked(AlbumModel albumModel) {
                mPresenter.albumClicked(albumModel);
            }
        });

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected RecyclerView.LayoutManager rvLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }


    @Override
    public void search(String response) {
        if(mPresenter == null) {
            mPresenter = supplyPresenter();
            mPresenter.attach(this);
        }
        mPresenter.search(response);
    }

    @Override
    public AlbumContract.Presenter supplyPresenter() {
        return new AlbumPresenter();
    }
}
