package com.nimtego.itunes.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.mvp_contracts.InformationAlbumContract;
import com.nimtego.itunes.presenter.InformationAlbumPresenter;
import com.nimtego.itunes.utils.IpTags;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InformationAlbumActivity
        extends BaseView<InformationAlbumContract.Presenter>
        implements InformationAlbumContract.View<InformationAlbumContract.Presenter>  {
    @BindView(R.id.fragment_image_view)
    ImageView albumImage;
    @BindView(R.id.fragment_album_name)
    TextView albumName;
    @BindView(R.id.fragment_artist_name)
    TextView artistName;
    @BindView(R.id.fragment_album_information)
    TextView information;
    @BindView(R.id.song_list)
    ListView songList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_album);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.viewIsReady();
    }

    @Override
    public InformationAlbumContract.Presenter supplyPresenter() {
        Intent intent = getIntent();
        String id = intent.getStringExtra(IpTags.ALBUM_ID.toString());
        return new InformationAlbumPresenter(id);
    }

    @Override
    public void setImageAlbum(String url) {
        Picasso.get().load(url.replace("100x100", "600x600"))
                .placeholder(R.drawable.baseline_update_black_48dp)
                .error(R.drawable.ic_launcher_background)
                .into(albumImage);
    }

    @Override
    public void setArtistName(String name) {
        artistName.setText(name);
    }

    @Override
    public void setAlbumName(String nameAlbum) {
        albumName.setText(nameAlbum);
    }

    @Override
    public void setSongList(List<String> songs) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
               android.R.layout.simple_list_item_1, songs);
        songList.setAdapter(adapter);
    }

    @Override
    public void setAlbumInformation(String information) {
        this.information.setText(information);
    }
}
