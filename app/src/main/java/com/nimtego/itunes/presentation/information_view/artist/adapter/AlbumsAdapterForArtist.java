package com.nimtego.itunes.presentation.information_view.artist.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.information_view.album.model.AlbumDetailsModel;
import com.nimtego.itunes.presentation.main.albums.AlbumAdapter;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumsAdapterForArtist extends RecyclerView.Adapter<AlbumsAdapterForArtist.ViewHolder> {

    public interface OnItemClickListener {
        void onUserItemClicked(AlbumModel albumModel);
    }

    private List<AlbumModel> models;
    private OnItemClickListener onItemClickListener;

    public AlbumsAdapterForArtist(List<AlbumModel> model, Context parent) {
        this.models = model;
    }

    @Override
    public AlbumsAdapterForArtist.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_form_for_artist, parent, false);
        return new AlbumsAdapterForArtist.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final AlbumsAdapterForArtist.ViewHolder holder, final int position) {
        final AlbumModel albumModel = this.models.get(position);
        holder.albumName.setText(albumModel.getAlbumName());
        holder.artistName.setText(albumModel.getAlbumArtistName());
        holder.pb.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AlbumsAdapterForArtist.this.onItemClickListener != null) {
                    AlbumsAdapterForArtist.this.onItemClickListener.onUserItemClicked(albumModel);
                }
            }
        });
        Picasso.get().load(models.get(position).getAlbumArtWorkUrl()
                .replace("100x100", "200x200"))
                .into(holder.albumImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (holder.pb != null)
                            holder.pb.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        if (holder.pb != null)
                            holder.pb.setVisibility(View.GONE);
                    }
                });
        holder.cv.setCardElevation(5);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        if (models == null)
            return 0;
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumImage;
        TextView albumName;
        TextView artistName;
        ProgressBar pb;
        CardView cv;
        ConstraintLayout card;

        public ViewHolder(View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.album_image);
            albumName = itemView.findViewById(R.id.artist_name);
            artistName = itemView.findViewById(R.id.album_name);
            pb = itemView.findViewById(R.id.image_progress_bar);
            card = itemView.findViewById(R.id.card);
            cv = itemView.findViewById(R.id.cv);

        }
    }
}