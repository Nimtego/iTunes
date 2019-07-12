package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.MusicTabPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.RouterProvider
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class MusicTabFragment : BaseFragment(), TabContentView, RouterProvider, BackButtonListener {

    override val layoutRes: Int = R.layout.bottom_parent_tab_fragment

    private var parentContainerRecyclerView: RecyclerView? = null

    @field:[Inject Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)]
    internal lateinit var tabMusicRouter: Router

    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    internal lateinit var parentRouter: Router

    @Inject
    @InjectPresenter
    internal lateinit var presenter: MusicTabPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): MusicTabPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRV()
        this.presenter.viewIsReady(getContainerName())
    }

    private fun getContainerName(): String {
        return arguments.getString(TAB_NAME)
    }

    override fun getRouter(): Router {
        return tabMusicRouter
    }

    override fun onBackPressed(): Boolean {
        tabMusicRouter.exit()
//        this.presenter.onBackPressed()
        return true
    }

    protected fun initRV() {
        this.parentContainerRecyclerView = view
                ?.findViewById(R.id.recycler_view_parent_tab_container)
        this.parentContainerRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MusicTabFragment.context,
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

    override fun showViewState(data: List<ParentTabModelContainer<ChildViewModel>>) {
        this.parentContainerRecyclerView?.apply {
            adapter = ParentTabAdapter(data).apply {
                setOnItemClickListener(presenter)
            }
        }
    }

    companion object {
        fun getInstance(): MusicTabFragment {
            val fragment = MusicTabFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, TAB)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TAB_NAME"
        const val TAB = "MUSIC_TAB"
    }
}