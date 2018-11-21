package com.nimtego.itunes.presentation.information_view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseView;
import com.nimtego.itunes.presentation.information_view.album.AlbumInformationContract;
import com.nimtego.itunes.presentation.information_view.album.AlbumInformationFragment;

import static com.nimtego.itunes.presentation.utils.IpTags.ALBUM_ID;

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
        AlbumInformationContract.View fragment = AlbumInformationFragment.newInstance(getIntent().getExtras().getString(ALBUM_ID.name()));
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frg, (android.support.v4.app.Fragment) fragment).commit();
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
