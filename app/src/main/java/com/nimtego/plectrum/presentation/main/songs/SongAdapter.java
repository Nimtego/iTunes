package com.nimtego.plectrum.presentation.main.songs;

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

import com.nimtego.plectrum.R;
import com.nimtego.plectrum.presentation.main.model.SongModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

@Deprecated
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onUserItemClicked(SongModel albumModel);
    }

    private List<SongModel> models;
    private OnItemClickListener onItemClickListener;

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
        final SongModel songModel = this.models.get(position);
        holder.songName.setText(songModel.getTrackName());
        holder.songAlbumName.setText(songModel.getTrackAlbumName());
        holder.songArtistName.setText(songModel.getTrackArtistName());
        holder.pb.setVisibility(View.VISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SongAdapter.this.onItemClickListener != null) {
                    SongAdapter.this.onItemClickListener.onUserItemClicked(songModel);
                }
            }
        });
        Picasso.get().load(models.get(position).getTrackArtwork().replace("100x100", "200x200"))
                .into(holder.songImage, new Callback() {
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
        ImageView songImage;
        TextView songName;
        TextView songAlbumName;
        TextView songArtistName;
        ProgressBar pb;
        CardView cv;
        ConstraintLayout card;

        public ViewHolder(View itemView) {
            super(itemView);
            songImage = itemView.findViewById(R.id.song_image);
            songName = itemView.findViewById(R.id.song_name);
            songAlbumName = itemView.findViewById(R.id.song_album_name);
            songArtistName = itemView.findViewById(R.id.song_artist_name);
            pb = itemView.findViewById(R.id.image_progress_bar);
            card = itemView.findViewById(R.id.card);
            cv = itemView.findViewById(R.id.cv);

        }
    }
}
