package com.nimtego.itunes.view;

import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.service.ResultEntity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private final Context paren;
    private List<ResultEntity> posts;

    public PostAdapter(List<ResultEntity> posts, Context parent) {
        this.posts = posts;
        this.paren = parent;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card_form, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ResultEntity post = posts.get(position);
        holder.albumName.setText(posts.get(position).getCollectionName());
        holder.artistName.setText(posts.get(position).getArtistName());
        Picasso.get().load(posts.get(position).getArtworkUrl100())
                .placeholder(R.drawable.baseline_search_black_18dp) //показываем что-то, пока не загрузится указанная картинка
                .error(R.drawable.ic_launcher_background) // показываем что-то, если не удалось скачать картинку
                .into(holder.albumImage);
/*        Picasso.get()
                .load(posts.get(position).getArtworkUrl60())
                .networkPolicy(NetworkPolicy.OFFLINE)//user this for offline support
                .into(holder.albumImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(posts.get(position).getArtworkUrl60())
                                .error(android.R.drawable.stat_notify_error)//user this for offline support
                                .into(holder.albumImage);
                    }
                });
        Picasso.get().load(posts.get(position).getCollectionViewUrl()).into(holder.albumImage);*/
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
