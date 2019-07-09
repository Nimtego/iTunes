package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.MoreSectionPresenter
import com.nimtego.plectrum.presentation.mvp.view.MoreSectionView
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import com.nimtego.plectrum.presentation.ui.widget.adapters.SongAdapter
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class MoreSectionFragment : BaseFragment(), MoreSectionView, BackButtonListener {

    override val layoutRes: Int = R.layout.more_section_fragment

    private var parentContainerRecyclerView: RecyclerView? = null

    @field:[Inject Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)]
    internal lateinit var tabMusicRouter: Router

    @Inject
    @InjectPresenter
    internal lateinit var presenter: MoreSectionPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): MoreSectionPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onBackPressed(): Boolean {
        this.presenter.onBackPressed()
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRV()
//        presenter.router = (this.parentFragment as RouterProvider).getRouter()
        presenter.viewReady(arguments.getString("SECTION"))
    }

    protected fun initRV() {
        this.parentContainerRecyclerView = this.view
                ?.findViewById(R.id.recycler_view_more_section)

        this.parentContainerRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MoreSectionFragment.context,
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

    override fun showViewState(data: List<Song>) {
        this.parentContainerRecyclerView?.apply {
            adapter = SongAdapter(data, this.context)
        }
    }

    companion object {
        fun getInstance(sectionName: String) : MoreSectionFragment {
            val fragment = MoreSectionFragment()
            val arguments = Bundle()
            arguments.putString(TAB_NAME, sectionName)
            fragment.arguments = arguments
            return fragment
        }

        const val TAB_NAME = "SECTION"
    }
}