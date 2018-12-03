package com.nimtego.itunes.presentation.information_view.song;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseFragment;
import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static com.nimtego.itunes.presentation.utils.IpTags.ALBUM_ID;
import static com.nimtego.itunes.presentation.utils.IpTags.SONG_ID;

public class SongInformationFragment
        extends BaseFragment<SongInformationContract.Presenter>
        implements SongInformationContract.View<SongInformationContract.Presenter> {

    private TextView price;
    private TextView date;
    private TextView artistName;
    private TextView songs;
    private ImageView albumImage;
    private TextView information;
    private ProgressBar pb;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static SongInformationContract.View newInstance(final String content) {
        final SongInformationContract.View fragment = new SongInformationFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(SONG_ID.name(), content);
        ((BaseFragment) fragment).setArguments(arguments);
        return fragment;
    }

    @Override
    public SongInformationContract.Presenter supplyPresenter() {
        return new SongInformationPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_album_form, container, false);
        artistName = view.findViewById(R.id.author);
        date = view.findViewById(R.id.release_date);
        price = view.findViewById(R.id.price);
        songs = view.findViewById(R.id.songs);
        information = view.findViewById(R.id.information);
        albumImage = view.findViewById(R.id.image_album);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);

        pb = view.findViewById(R.id.image_progress_bar);
        mPresenter.viewReady(getArguments().getString(SONG_ID.name()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* this.mPresenter.viewReady(getArguments().getString(ALBUM_ID.name()));*/
    }

    @Override
    public void render(AlbumDetailsModel albumDetailsModel) {
        artistName.setText(albumDetailsModel.getAlbumArtistName());
        date.setText(albumDetailsModel.getReleaseDate());
        price.setText(String.valueOf(albumDetailsModel.getCollectionPrice()));
        information.setText(albumDetailsModel.getWikiInformation());
        StringBuilder sb = new StringBuilder();
        albumDetailsModel.getSongs().forEach(s -> sb.append(s.getTrackName()).append("\n\n"));
        songs.setText(sb);
        collapsingToolbarLayout.setTitle(albumDetailsModel.getAlbumName());
        Picasso.get().load(albumDetailsModel.getAlbumArtwork()
                .replace("100x100", "400x400"))
                .into(albumImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (pb != null)
                            pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        if (pb != null)
                            pb.setVisibility(View.GONE);
                    }
                });
    }
}
