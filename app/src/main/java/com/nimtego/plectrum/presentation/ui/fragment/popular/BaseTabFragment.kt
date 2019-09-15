package com.nimtego.plectrum.presentation.ui.fragment.popular

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.nimtego.plectrum.presentation.utils.BackButtonListener

abstract class BaseTabFragment : BaseFragment(), TabContentView, BackButtonListener {

    override val layoutRes: Int = R.layout.bottom_parent_tab_fragment

    protected var parentContainerRecyclerView: RecyclerView? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRV()
    }

    private fun initRV() {
        this.parentContainerRecyclerView = view
                ?.findViewById(R.id.recycler_view_parent_tab_container)
        this.parentContainerRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BaseTabFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false)
            addItemDecoration(provideItemDecorator())
        }
    }

    abstract fun provideItemDecorator(): RecyclerView.ItemDecoration

}