package com.nimtego.plectrum.presentation.ui.widget.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator

class DashBoardTabAdapter(
        private val models: BaseParentViewModel<ChildViewModel>,
        parent: Context
) : RecyclerView.Adapter<DashBoardTabAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private var onItemClickListener: OnItemClickListener? = null

    //todo change onclick: for section
    interface OnItemClickListener {
        fun sectionClicked(sectionName: String)
        fun childItemClicked(id: String)
    }
    fun onUserItemClicked(childViewModel: ChildViewModel) {
        onItemClickListener?.childItemClicked(childViewModel.id())
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.layout_tab_content_parent_item, parent, false)
        val holder = ViewHolder(view)
        view.findViewById<LinearLayout>(R.id.section_header).setOnClickListener{ _: View ->
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                this.models.sectionViewModel.get(adapterPosition).let {
                    this.onItemClickListener?.sectionClicked(it.title())
                }
            }
        }
        return holder
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
            adapter = SectionChildAdapter(sectionModel.getModels(), this.context).apply {
                    setOnItemClickListener(object : SectionChildAdapter.OnItemClickListener {
                        override fun onUserItemClicked(childViewModel: ChildViewModel) {
                            this@DashBoardTabAdapter.onItemClickListener?.childItemClicked(childViewModel.id())
                        }
                    })
            }
            recycledViewPool = viewPool
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return models.sectionViewModel.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sectionTitle: TextView = itemView.findViewById(R.id.text_view_header)
        var childRecyclerView: RecyclerView? = null

        init {
            childRecyclerView = itemView.findViewById(R.id.recycler_view_child_container)
        }
    }
}