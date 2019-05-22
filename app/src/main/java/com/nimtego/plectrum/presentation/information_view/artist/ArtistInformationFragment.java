package com.nimtego.plectrum.presentation.information_view.artist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nimtego.plectrum.R;
import com.nimtego.plectrum.presentation.base.BaseFragment;
import com.nimtego.plectrum.presentation.information_view.artist.adapter.AlbumsAdapterForArtist;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModel;
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK;
import com.nimtego.plectrum.presentation.main.model.AlbumModel;
import com.nimtego.plectrum.presentation.main.model.AlbumModelK;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static com.nimtego.plectrum.presentation.utils.IpTags.ARTIST_ID;

public class ArtistInformationFragment
        extends BaseFragment<ArtistInformationContract.Presenter>
        implements ArtistInformationContract.View<ArtistInformationContract.Presenter> {

    private TextView price;
    private TextView date;
    private TextView artistName;
    private TextView songs;
    private ImageView albumImage;
    private TextView information;
    private ProgressBar pb;
    private RecyclerView albumsRv;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static ArtistInformationContract.View newInstance(final String content) {
        final ArtistInformationContract.View fragment = new ArtistInformationFragment();
        final Bundle arguments = new Bundle();
        arguments.putString(ARTIST_ID.name(), content);
        ((BaseFragment) fragment).setArguments(arguments);
        return fragment;
    }

    @Override
    public ArtistInformationContract.Presenter supplyPresenter() {
        return new ArtistInformationPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_artist_form, container, false);
        artistName = view.findViewById(R.id.author);
        date = view.findViewById(R.id.release_date);
        price = view.findViewById(R.id.price);
        songs = view.findViewById(R.id.songs);
        information = view.findViewById(R.id.information);
        albumImage = view.findViewById(R.id.image_album);
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar);
        initRV(view, container);

        pb = view.findViewById(R.id.image_progress_bar);
        mPresenter.viewReady(getArguments().getString(ARTIST_ID.name()));
        return view;
    }

    protected void initRV(View view, ViewGroup viewGroup) {
        albumsRv = view.findViewById(R.id.album_rv);
        albumsRv.setHasFixedSize(true);
        albumsRv.setLayoutManager(new LinearLayoutManager(viewGroup.getContext()));
        ViewCompat.setNestedScrollingEnabled(albumsRv, false);
        albumsRv.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void render(ArtistDetailsModelK artistDetailsModel) {
        artistName.setText(artistDetailsModel.getArtistName());
        price.setText(artistDetailsModel.getArtistArtwork());
        information.setText(artistDetailsModel.getWikiInformation());
        collapsingToolbarLayout.setTitle(artistDetailsModel.getArtistName());
        AlbumsAdapterForArtist albumsAdapter = new AlbumsAdapterForArtist(artistDetailsModel.getAlbums(),
                this.getActivity());
        albumsAdapter.setOnItemClickListener(new AlbumsAdapterForArtist.OnItemClickListener() {
            @Override
            public void onUserItemClicked(AlbumModelK albumModel) {
                mPresenter.albumClicked(albumModel);
            }
        });
        albumsRv.setAdapter(albumsAdapter);

        Picasso.get().load(artistDetailsModel.getArtistArtwork()
                .replace("135x135", "570x570"))
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
