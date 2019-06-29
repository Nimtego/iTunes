package com.nimtego.plectrum.presentation.ui.widget.behavior

import android.content.Context
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout


class BottomNavigationBehavior : CoordinatorLayout.Behavior<BottomNavigationView> {

    constructor() : super() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun layoutDependsOn(parent: CoordinatorLayout, child: BottomNavigationView, dependency: View): Boolean {
        return dependency is FrameLayout
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: BottomNavigationView, target: View, dx: Int, dyConsumed: Int, consumed: IntArray) {
        if (dyConsumed > 0) {
            slideDown(child)
        }
        if (dyConsumed < 0) {
            slideUp(child)
        }
    }

    private fun slideUp(child: View) {
        child.clearAnimation()
        child.animate().translationY(VIEW_HIDDEN_HEIGHT).duration = ANIMATION_DURATION;
    }

    private fun slideDown(child: View) {
        child.clearAnimation()
        child.animate().translationY(VIEW_SHOWN_HEIGHT).duration = ANIMATION_DURATION
    }

    companion object {
        private const val VIEW_HIDDEN_HEIGHT = 0f
        private const val VIEW_SHOWN_HEIGHT: Float = 200f
        private const val ANIMATION_DURATION = 150L
    }
}