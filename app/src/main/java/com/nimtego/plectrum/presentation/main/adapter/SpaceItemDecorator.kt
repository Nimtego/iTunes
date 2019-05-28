package com.nimtego.plectrum.presentation.main.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpaceItemDecorator(private val spanCount: Int = 0, private val spacing: Int, private val includeEdge: Boolean = true) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)
        val column = if (spanCount > 0) position % spanCount else 0
        if (includeEdge) {
            if (spanCount > 0) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                if (position == 0) {
                    outRect.left = spacing
                }
                outRect.right = spacing
                outRect.bottom = spacing
                outRect.top = spacing
            }
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}