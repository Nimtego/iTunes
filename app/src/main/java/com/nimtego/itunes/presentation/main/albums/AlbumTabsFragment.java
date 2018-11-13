package com.nimtego.itunes.presentation.main.albums;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.ArrayList;
import java.util.Collection;

public class AlbumTabsFragment
        extends MainTabsFragment<AlbumContract.Presenter>
        implements AlbumContract.View<AlbumContract.Presenter> {

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
        mPresenter.search(response);
    }

    @Override
    public AlbumContract.Presenter supplyPresenter() {
        return new AlbumPresenter();
    }


/*    @Override
    protected RecyclerView.LayoutManager rvLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    @Override
    public void setSearchList(MainDataModel dataModel) {
        RecyclerView.Adapter adapter = new AlbumAdapter(dataModel.getAlbumModels(),
                this.getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public AlbumContract.Presenter supplyPresenter() {
        return new AlbumPresenter();
    }


    @Override
    public void render(Collection albumModel) {

    }

    @Override
    public void search(String response) {

    }*/
}
