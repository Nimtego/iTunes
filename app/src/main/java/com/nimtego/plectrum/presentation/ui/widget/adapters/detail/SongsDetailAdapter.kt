package com.nimtego.plectrum.presentation.ui.widget.adapters.detail

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel

class SongsDetailAdapter(
        private val models: List<SongDetailModel>
) : RecyclerView.Adapter<SongsDetailAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onUserItemClicked(songDetailModel: SongDetailModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_song_form_for_album_detail, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener { _: View ->
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                this.models[adapterPosition].let {
                    this.onItemClickListener?.onUserItemClicked(it)
                }
            }
        }
        return holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val songModel = this.models[position]
        holder.songName.text = songModel.trackName
//        holder.pb.visibility = View.VISIBLE
//        holder.itemView.setOnClickListener {
//            if (this.onItemClickListener != null) {
//                this.onItemClickListener!!.onUserItemClicked(sectionModel)
//            }
//        }
        //todo
//        Picasso.get().load(models[position].trackArtwork
//                .replace("100x100", "200x200"))
//                .into(holder.songImage, object : Callback {
//                    override fun onSuccess() {
//                        holder.pb.visibility = View.GONE
//                    }
//
//                    override fun onError(e: Exception) {
//                        holder.pb.visibility = View.GONE
//                    }
//                })
//        holder.cv.cardElevation = 5f
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return models.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songName: TextView = itemView.findViewById(R.id.song_name)
    }
}