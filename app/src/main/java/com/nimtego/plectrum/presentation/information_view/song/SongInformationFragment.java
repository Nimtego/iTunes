package com.nimtego.plectrum.presentation.information_view.song;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nimtego.plectrum.R;
import com.nimtego.plectrum.presentation.base.BaseFragment;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModel;
import com.nimtego.plectrum.presentation.information_view.song.model.SongDetailsModelK;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static com.nimtego.plectrum.presentation.utils.IpTags.SONG_ID;

@Deprecated
public class SongInformationFragment
        extends BaseFragment<SongInformationContract.Presenter>
        implements SongInformationContract.View<SongInformationContract.Presenter> {

    private ImageView songImage;
    private TextView information;

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
        View view = inflater.inflate(R.layout.information_song_form, container, false);
        information = view.findViewById(R.id.author);
        songImage = view.findViewById(R.id.song_image);

        mPresenter.viewReady(getArguments().getString(SONG_ID.name()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* this.mPresenter.viewReady(getArguments().getString(ALBUM_ID.name()));*/
    }

    @Override
    public void render(SongDetailsModelK songDetailsModel) {
        StringBuilder sb = new StringBuilder();
        sb.append(songDetailsModel.getSongName()).append("\n\n")
                .append(songDetailsModel.getSongAlbumName()).append("\n\n")
                .append(songDetailsModel.getSongArtistName()).append("\n\n")
                .append(songDetailsModel.getReleaseDate()).append("\n\n")
                .append(songDetailsModel.getSongPrice()).append("\n\n");
        information.setText(sb);
        Picasso.get().load(songDetailsModel.getSongArtwork()
                .replace("100x100", "400x400"))
                .into(songImage, new Callback() {
                    @Override
                    public void onSuccess() {
                  /*      if (pb != null)
                            pb.setVisibility(View.GONE);*/
                    }

                    @Override
                    public void onError(Exception e) {
                   /*     if (pb != null)
                            pb.setVisibility(View.GONE);*/
                    }
                });
    }
}
