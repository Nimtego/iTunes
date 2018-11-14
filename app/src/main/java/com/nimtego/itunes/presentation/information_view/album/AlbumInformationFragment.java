package com.nimtego.itunes.presentation.information_view.album;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BaseFragment;
import com.nimtego.itunes.presentation.information_view.DetailedInformationActivity;
import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;

import static com.nimtego.itunes.presentation.utils.IpTags.ALBUM_ID;

public class AlbumInformationFragment
        extends BaseFragment<AlbumInformationContract.Presenter>
        implements AlbumInformationContract.View<AlbumInformationContract.Presenter> {

    private TextView albumName;

    public static AlbumInformationContract.View newInstance(final String content) {
        final AlbumInformationContract.View fragment = new AlbumInformationFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(ALBUM_ID.name(), content);
        ((BaseFragment)fragment).setArguments(arguments);
        return fragment;
    }
    @Override
    public AlbumInformationContract.Presenter supplyPresenter() {
        return new AlbumInformationPresenter();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_album_form, container, false);
        albumName = view.findViewById(R.id.album_name);
        mPresenter.viewReady(getArguments().getString(ALBUM_ID.name()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mPresenter.viewReady(getArguments().getString(ALBUM_ID.name()));
    }

    @Override
    public void render(AlbumDetailsModel albumDetailsModel) {
        albumName.setText(albumDetailsModel.getAlbumName());
    }
}
