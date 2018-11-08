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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card_form, parent, false);
        return new SongAdapter.ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final SongAdapter.ViewHolder holder, final int position) {
        holder.songName.setText(models.get(position).getTrackName());
        holder.songAlbumName.setText(models.get(position).getTrackAlbumName());
        holder.songArtistName.setText(models.get(position).getTrackArtistName());
        Picasso.get().load(models.get(position).getTrackArtwork().replace("100x100", "200x200"))
                .placeholder(R.drawable.baseline_update_black)
                .error(R.drawable.ic_launcher_background)
                .into(holder.songImage);
        holder.cv.setCardElevation(5);
    }

    @Override
    public int getItemCount() {
        if (models == null)
            return 0;
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView songImage;
        TextView songName;
        TextView songAlbumName;
        TextView songArtistName;
        CardView cv;
        ConstraintLayout card;

        public ViewHolder(View itemView) {
            super(itemView);
            songImage = itemView.findViewById(R.id.song_image);
            songName = itemView.findViewById(R.id.song_name);
            songAlbumName = itemView.findViewById(R.id.song_album_name);
            songArtistName = itemView.findViewById(R.id.song_artist_name);
            card = itemView.findViewById(R.id.card);
            cv = itemView.findViewById(R.id.cv);

        }
    }
}
