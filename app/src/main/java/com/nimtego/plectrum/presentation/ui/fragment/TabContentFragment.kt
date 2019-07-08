package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.presenters.RouterProvider
import com.nimtego.plectrum.presentation.mvp.presenters.TabContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import com.nimtego.plectrum.presentation.ui.widget.adapters.DashBoardTabAdapter
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class TabContentFragment : BaseFragment(), TabContentView, RouterProvider, BackButtonListener {

    override val layoutRes: Int = R.layout.bottom_parent_tab_fragment

    private var parentContainerRecyclerView: RecyclerView? = null

    private lateinit var router: Router

    @Inject
    @InjectPresenter
    internal lateinit var presenter: TabContentPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): TabContentPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        this.router = (this.parentFragment as RouterProvider).getRouter()
//        this.presenter.router = this.router
        initRV()
        this.presenter.viewIsReady(getContainerName())
    }

    private fun getContainerName(): String {
        return arguments.getString(TAB_NAME)
    }

    override fun getRouter(): Router {
        return router
    }

    override fun onBackPressed(): Boolean {
//        val fragment = childFragmentManager.findFragmentById(R.id.bottom_navigation_container)
//        if (fragment != null
//                && fragment is BackButtonListener
//                && (fragment as BackButtonListener).onBackPressed()) {
//            return true
//        } else {
//            (activity as RouterProvider).getRouter().exit()
        router.exit()
        return true
    }

    protected fun initRV() {
        this.parentContainerRecyclerView = view
                ?.findViewById(R.id.recycler_view_parent_tab_container)
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
            adapter = DashBoardTabAdapter(data, this.context).apply {
                setOnItemClickListener(presenter)
            }
        }
    }

    companion object {
        fun getInstance(tabName: String): TabContentFragment {
            val fragment = TabContentFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, tabName)
            fragment.arguments = arguments

            return fragment
        }

        val TAB_NAME = "TAB_NAME"
    }
}