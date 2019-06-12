package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.domain.interactor.DashBoardInteractorK
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import com.nimtego.plectrum.presentation.mvp.presenters.TabContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel
import com.nimtego.plectrum.presentation.ui.widget.adapters.DashBoardTabAdapter

class TabContentFragment : MvpAppCompatFragment(), TabContentView {

    private var parentContainerRecyclerView: RecyclerView? = null

    @InjectPresenter
    internal lateinit var presenter: TabContentPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): TabContentPresenter {
        val dashBoardInteractor = App.INSTANCE.getRepository()
        return TabContentPresenter(App.INSTANCE.getRouter(), 5, dashBoardInteractor)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dashboard_music_tab_fragment, container, false)
        initRV(view, container)
        return view
    }

    protected fun initRV(view: View, viewGroup: ViewGroup?) {
        this.parentContainerRecyclerView = view.findViewById(R.id.recycler_view_music_container)

        this.parentContainerRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@TabContentFragment.context,
                    LinearLayoutManager.VERTICAL,
                    false)
            addItemDecoration(SpaceItemDecorator(spacing = 32,
                                                 spanCount = 1,
                                                 paddingTop = 24,
                                                 paddingBottom = 24))
//            itemAnimator = DefaultItemAnimator()
        }
    }

//Mark: view override
    override fun showViewState(data: BaseParentViewModel<ChildViewModel>) {
        this.parentContainerRecyclerView?.apply {
            adapter = DashBoardTabAdapter(data, this.context)
        }
    }

    companion object {
        fun getInstance() = TabContentFragment()
    }
}