package com.nimtego.itunes.presentation.main.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.SongModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    private List<SongModel> models;

    public SongAdapter(List<SongModel> model, Context parent) {
        this.models = model;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card_form, parent, false);
        return new SongAdapter.ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final SongAdapter.ViewHolder holder, final int position) {
        holder.albumName.setText(models.get(position).getSongAlbumName());
        holder.artistName.setText(models.get(position).getSongAlbumName());
        Picasso.get().load(models.get(position).getSongArtwork().replace("100x100", "200x200"))
                .placeholder(R.drawable.baseline_update_black)
                .error(R.drawable.ic_launcher_background)
                .into(holder.albumImage);
        holder.cv.setCardElevation(5);
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
        CardView cv;
        ConstraintLayout card;

        public ViewHolder(View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.album_image);
            albumName = itemView.findViewById(R.id.artist_name);
            artistName = itemView.findViewById(R.id.album_name);
            card = itemView.findViewById(R.id.card);
            cv = itemView.findViewById(R.id.cv);

        }
    }
}
