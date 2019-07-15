package com.nimtego.plectrum.presentation.ui.widget.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class MoreSectionAdapter(private val models: ParentTabModelContainer<ChildViewModel>, parent: Context) : RecyclerView.Adapter<MoreSectionAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onUserItemClicked(childViewModel: ChildViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_song_card_form, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val childViewModel = this.models.getModels().get(position)
        holder.songName.text = childViewModel.mainName()
        holder.songAlbumName.text = childViewModel.minorName()
        //todo new model wrapper
        holder.songArtistName.text = childViewModel.id()
        holder.pb!!.visibility = View.VISIBLE
        holder.itemView.setOnClickListener {
            this@MoreSectionAdapter.onItemClickListener?.onUserItemClicked(childViewModel)
        }
        Picasso.get().load(models.getModels()[position].imageUrl().replace("100x100", "200x200"))
                .into(holder.songImage, object : Callback {
                    override fun onSuccess() {
                        holder.pb?.visibility = View.GONE
                    }

                    override fun onError(e: Exception) {
                        holder.pb?.visibility = View.GONE
                    }
                })
        holder.cv.cardElevation = 5f
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return models.getModels().size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var songImage: ImageView
        var songName: TextView
        var songAlbumName: TextView
        var songArtistName: TextView
        var pb: ProgressBar? = null
        var cv: CardView
        var card: ConstraintLayout

        init {
            songImage = itemView.findViewById(R.id.song_image)
            songName = itemView.findViewById(R.id.song_name)
            songAlbumName = itemView.findViewById(R.id.song_album_name)
            songArtistName = itemView.findViewById(R.id.song_artist_name)
            pb = itemView.findViewById(R.id.image_progress_bar)
            card = itemView.findViewById(R.id.card)
            cv = itemView.findViewById(R.id.cv)

        }
    }
}