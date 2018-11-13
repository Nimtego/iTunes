package com.nimtego.itunes.presentation.main.adapter;

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

import java.util.List;

public abstract class BaseMainViewAdapter<E> extends RecyclerView.Adapter<BaseMainViewAdapter.BaseViewHolder>{

    private List<E> models;

    public BaseMainViewAdapter(List<E> models, Context parent) {
        this.models = models;
    }

    @Override
    public BaseMainViewAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(getItemView(), parent, false);
        return getHolder(v);
    }

    protected abstract BaseViewHolder getHolder(View v);

    protected abstract int getItemView();

    @Override
    public int getItemCount() {
        if (models == null)
            return 0;
        return models.size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);

        }
    }
}
