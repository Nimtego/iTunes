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
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.MainDataModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public abstract class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    protected MainDataModel model;

    public MainAdapter(MainDataModel model, Context parent) {
        this.model = model;
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