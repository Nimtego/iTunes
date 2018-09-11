package com.nimtego.itunes.view;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.service.ResultEntity;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<ResultEntity> posts;

    public PostAdapter(List<ResultEntity> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card_form, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ResultEntity post = posts.get(position);
        holder.albumName.setText(posts.get(position).getCollectionName());
        holder.artistName.setText(posts.get(position).getArtistName());
        /*holder.albumImage.setImageResource(posts.get(position).getArtistViewUrl());*/
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumImage;
        TextView albumName;
        TextView artistName;

        public ViewHolder(View itemView) {
            super(itemView);
            albumImage = itemView.findViewById(R.id.album_image);
            albumName = itemView.findViewById(R.id.artist_name);
            artistName = itemView.findViewById(R.id.album_name);
        }
    }
}
