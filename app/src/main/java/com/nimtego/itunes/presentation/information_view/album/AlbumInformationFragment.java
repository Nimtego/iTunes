package com.nimtego.itunes.presentation.information_view.album;

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
import com.nimtego.itunes.presentation.information_view.model.AlbumDetailsModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static com.nimtego.itunes.presentation.utils.IpTags.ALBUM_ID;

public class AlbumInformationFragment
        extends BaseFragment<AlbumInformationContract.Presenter>
        implements AlbumInformationContract.View<AlbumInformationContract.Presenter> {

    private TextView information;
    private TextView artistName;
    private ImageView albumImage;
    private ProgressBar pb;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static AlbumInformationContract.View newInstance(final String content) {
        final AlbumInformationContract.View fragment = new AlbumInformationFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(ALBUM_ID.name(), content);
        ((BaseFragment) fragment).setArguments(arguments);
        return fragment;
    }

    @Override
    public AlbumInformationContract.Presenter supplyPresenter() {
        return new AlbumInformationPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_album_form, container, false);
        artistName = view.findViewById(R.id.artist_name);
        information = view.findViewById(R.id.information);
        albumImage = view.findViewById(R.id.image_album);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);

        pb = view.findViewById(R.id.image_progress_bar);
        mPresenter.viewReady(getArguments().getString(ALBUM_ID.name()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* this.mPresenter.viewReady(getArguments().getString(ALBUM_ID.name()));*/
    }

    @Override
    public void render(AlbumDetailsModel albumDetailsModel) {
        StringBuilder sb = new StringBuilder();
        sb.append("Price - ")
                .append(albumDetailsModel.getCollectionPrice())
        .append("\n").append("Data - ").append(albumDetailsModel.getReleaseDate());
        information.setText(String.valueOf(sb));
        artistName.setText(albumDetailsModel.getAlbumArtistName());
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
