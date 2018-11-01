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
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    //private final Context paren;
    private List<AlbumModel> posts;

    public AlbumAdapter(List<AlbumModel> posts, Context parent) {
        this.posts = posts;
        //this.paren = parent;
    }

    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card_form, parent, false);
        return new AlbumAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AlbumAdapter.ViewHolder holder, final int position) {
        // final ResultEntity post = posts.get(position);
        holder.albumName.setText(posts.get(position).getCollectionName());
        holder.artistName.setText(posts.get(position).getArtistName());
        Picasso.get().load(posts.get(position).getArtworkUrl100().replace("100x100", "200x200"))
                .placeholder(R.drawable.baseline_update_black)
                .error(R.drawable.ic_launcher_background)
                .into(holder.albumImage);
        holder.cv.setCardElevation(5);
        Random rand = new Random();

/*        holder.card.setBackgroundColor(Color.rgb(rand.nextInt(250)+ 100,
                rand.nextInt(250) + 100,
                rand.nextInt(250) + 100));*/
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