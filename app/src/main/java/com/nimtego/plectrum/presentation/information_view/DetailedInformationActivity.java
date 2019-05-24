package com.nimtego.plectrum.presentation.information_view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nimtego.plectrum.R;
import com.nimtego.plectrum.presentation.base.BaseView;
import com.nimtego.plectrum.presentation.information_view.album.AlbumInformationFragment;
import com.nimtego.plectrum.presentation.information_view.artist.ArtistInformationFragment;
import com.nimtego.plectrum.presentation.information_view.song.SongInformationFragment;
import com.nimtego.plectrum.presentation.utils.FragmentType;

import static com.nimtego.plectrum.presentation.utils.IpTags.ALBUM_ID;
import static com.nimtego.plectrum.presentation.utils.IpTags.ARTIST_ID;
import static com.nimtego.plectrum.presentation.utils.IpTags.SONG_ID;

@Deprecated
public class DetailedInformationActivity
        extends BaseView<DetailedInformationContract.Presenter>
        implements DetailedInformationContract.View<DetailedInformationContract.Presenter> {

    private static final String RESPONSE_NAME = "Name";

    public static Intent getCallingIntent(Context context, String name) {
        Intent callingIntent = new Intent(context, DetailedInformationActivity.class);
        callingIntent.putExtra(RESPONSE_NAME, name);
        return callingIntent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Fragment fragment = null;
        if (getIntent().getExtras() != null) {
            String type = getIntent().getExtras().getString(FragmentType.TYPE.name());
            assert type != null;
            if (type.equals(FragmentType.ALBUM.name()))
                fragment = (Fragment) AlbumInformationFragment
                        .newInstance(getIntent().getExtras().getString(ALBUM_ID.name()));
            if (type.equals(FragmentType.SONG.name()))
                fragment = (Fragment) SongInformationFragment
                        .newInstance(getIntent().getExtras().getString(SONG_ID.name()));
            if (type.equals(FragmentType.ARTIST.name()))
                fragment = (Fragment) ArtistInformationFragment
                        .newInstance(getIntent().getExtras().getString(ARTIST_ID.name()));
        }
        if (savedInstanceState == null && fragment != null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frg, fragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public DetailedInformationContract.Presenter supplyPresenter() {
        return new DetailedInformationPresenter();
    }

}
