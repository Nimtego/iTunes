package com.nimtego.plectrum.presentation.main.fragments

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.nimtego.plectrum.R


abstract class MainTabsFragmentK: MvpAppCompatFragment(), MainTabsView {

    protected var mRecyclerView: RecyclerView? = null
    protected var searchText: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null && arguments.containsKey(RESPONSE)) {
            searchText = arguments.getString(RESPONSE)
        } else {
            throw IllegalArgumentException("created through newInstance(...)!")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.my_tabs_fragment, container, false)
        initRV(view, container)
        return view
    }


    protected fun initRV(view: View, viewGroup: ViewGroup?) {
        mRecyclerView = view.findViewById(R.id.recycler_view)
        mRecyclerView!!.setHasFixedSize(true)
        mRecyclerView!!.layoutManager = rvLayoutManager(viewGroup!!.context)
        mRecyclerView!!.addItemDecoration(itemDecorator())
        mRecyclerView!!.itemAnimator = DefaultItemAnimator()
    }

    protected abstract fun itemDecorator(): RecyclerView.ItemDecoration

    protected abstract fun rvLayoutManager(context: Context): RecyclerView.LayoutManager

//    override fun isRvEmpty(): Boolean {
//        return if (mRecyclerView!!.adapter == null) true else mRecyclerView!!.adapter.itemCount == 0
//    }

    override fun clearList() {
        if (mRecyclerView != null)
            mRecyclerView!!.adapter = null
    }

//    override fun getCurrentSerch(): String? {
//        return searchText
//    }

    override fun setCurrentSearch(response: String) {
        this.searchText = response
    }

    companion object {

        protected val RESPONSE = "response_content"
    }
}
