package com.nimtego.plectrum.presentation.ui.widget.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View

class SearchViewScrollBehavior(context: Context?,
                               attrs: AttributeSet?
) : AppBarLayout.Behavior(context, attrs) {

// MARK: - Methods

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)

        if (dyConsumed > 0) {
            slideDown(child)
        }
        if (dyConsumed < 0) {
            slideUp(child)
        }
    }

    private fun stopNestedScrollIfNeeded(dy: Int, child: AppBarLayout, target: View?, type: Int) {
        if (type == ViewCompat.TYPE_NON_TOUCH) {
            val currOffset = topAndBottomOffset
            if (dy < 0 && currOffset == 0 || dy > 0 && currOffset == -child.totalScrollRange) {
                ViewCompat.stopNestedScroll(target!!, ViewCompat.TYPE_NON_TOUCH)
            }
        }
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout,
                                     directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    private fun slideUp(child: View) {
        child.clearAnimation()
        child.animate().translationY(VIEW_HIDDEN_HEIGHT).duration = ANIMATION_DURATION;
    }

    private fun slideDown(child: View) {
        child.clearAnimation()
        child.animate().translationY(VIEW_SHOWN_HEIGHT).duration = ANIMATION_DURATION
    }

// MARK: - Companion

    companion object {
        private const val VIEW_HIDDEN_HEIGHT = 0f
        private const val VIEW_SHOWN_HEIGHT: Float = -200f
        private const val ANIMATION_DURATION = 150L
    }
}