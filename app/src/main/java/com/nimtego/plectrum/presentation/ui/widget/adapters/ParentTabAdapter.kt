package com.nimtego.plectrum.presentation.ui.widget.adapters

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.ui.widget.behavior.StartSnapHelper
import com.nimtego.plectrum.presentation.ui.widget.behavior.SpaceItemDecorator

class ParentTabAdapter(
        private val models: List<ParentTabModelContainer<ChildViewModel>>
) : RecyclerView.Adapter<ParentTabAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()
    private var onItemClickListener: OnItemClickListener? = null

    //todo change onclick: for section
    interface OnItemClickListener {
        fun sectionClicked(section: ParentTabModelContainer<ChildViewModel>)
        fun childItemClicked(childViewModel: ChildViewModel)
    }

    fun onUserItemClicked(childViewModel: ChildViewModel) {
        onItemClickListener?.childItemClicked(childViewModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.layout_tab_content_parent_item, parent, false)
        val holder = ViewHolder(view)
        view.findViewById<LinearLayout>(R.id.section_header).setOnClickListener { _: View ->
            val adapterPosition = holder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                this.models[adapterPosition].let {
                    this.onItemClickListener?.sectionClicked(it)
                }
            }
        }
        return holder
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sectionModel = this.models[position]
        holder.sectionTitle.text = sectionModel.title()
        val childLayoutManager = LinearLayoutManager(
                holder.childRecyclerView?.context, LinearLayout.HORIZONTAL, false)

        holder.childRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = childLayoutManager
            addItemDecoration(SpaceItemDecorator(spacing = spacingItems))
            adapter = SectionChildAdapter(sectionModel.getModels()).apply {
                setOnItemClickListener(object : SectionChildAdapter.OnItemClickListener {
                    override fun onUserItemClicked(childViewModel: ChildViewModel) {
                        this@ParentTabAdapter.onItemClickListener?.childItemClicked(childViewModel)
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
        return models.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sectionTitle: TextView = itemView.findViewById(R.id.text_view_header)
        var childRecyclerView: RecyclerView? = null
         var snapHelper: StartSnapHelper = StartSnapHelper()

        init {
            this.childRecyclerView = itemView.findViewById(R.id.recycler_view_child_container)
            this.snapHelper. apply {
                attachToRecyclerView(childRecyclerView)
                spaceSize = spacingItems
            }
        }
    }

    companion object {
        const val spacingItems = 30
    }
}