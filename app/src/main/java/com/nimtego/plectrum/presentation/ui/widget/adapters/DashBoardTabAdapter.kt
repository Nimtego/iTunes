package com.nimtego.plectrum.presentation.ui.widget.adapters

import android.content.Context
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.nimtego.plectrum.R
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel

class DashBoardTabAdapter(
        private val models: BaseParentViewModel<ChildViewModel>,
        parent: Context
) : RecyclerView.Adapter<DashBoardTabAdapter.ViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()
    private var onItemClickListener: OnItemClickListener? = null

    //todo change onclick: for section
    interface OnItemClickListener {
        fun onUserItemClicked(model: Song)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.dashboard_parent_item_k, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sectionModel = this.models.sectionViewModel!![position]
        holder.sectionTitle.text = sectionModel.title()
        val childLayoutManager = LinearLayoutManager(
                holder.childRecyclerView?.context, LinearLayout.HORIZONTAL, false)
        holder.childRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = childLayoutManager
            addItemDecoration(SpaceItemDecorator(spacing = 30))
//            itemAnimator = DefaultItemAnimator()
            adapter = SectionChildAdapter(sectionModel.getModels(), this.context)
            recycledViewPool = viewPool
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return models?.sectionViewModel?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sectionTitle: TextView
        var childRecyclerView: RecyclerView? = null

        init {
            sectionTitle = itemView.findViewById(R.id.text_view_header)
            childRecyclerView = itemView.findViewById(R.id.recycler_view_child_container)
        }
    }
}