package com.nimtego.itunes.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.mvp_contracts.InformationAlbumContract;
import com.nimtego.itunes.presenter.InformationAlbumPresenter;
import com.nimtego.itunes.utils.IpTags;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class InformationAlbumActivity
        extends BaseView<InformationAlbumContract.Presenter>
        implements InformationAlbumContract.View<InformationAlbumContract.Presenter>  {
    @BindView(R.id.fragment_image_view)
    ImageView albumImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_album);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.viewIsReady();
    }

    @Override
    public InformationAlbumContract.Presenter supplyPresenter() {
        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra(IpTags.ALBUM_ID.toString()));
        return new InformationAlbumPresenter(id);
    }

    @Override
    public void setImageAlbum(String url) {
        Picasso.get().load(url)
                .placeholder(R.drawable.baseline_search_black_18dp)
                .error(R.drawable.ic_launcher_background)
                .into(albumImage);
    }

    @Override
    public void setArtistName(String name) {

    }

    @Override
    public void setAlbumName(String nameAlbum) {

    }
}
