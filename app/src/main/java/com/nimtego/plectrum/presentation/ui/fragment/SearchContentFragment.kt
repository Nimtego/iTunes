package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.song.MusicTabModel
import com.nimtego.plectrum.presentation.mvp.presenters.SearchContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.SearchContentView
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import com.nimtego.plectrum.presentation.ui.widget.adapters.MoreSectionAdapter
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import kotlinx.android.synthetic.main.search_content_fragment.*
import javax.inject.Inject

class SearchContentFragment : BaseFragment(), SearchContentView, BackButtonListener {

    override val layoutRes: Int = R.layout.search_content_fragment

    @Inject
    @InjectPresenter
    internal lateinit var presenter: SearchContentPresenter

    private var searchContentRv: RecyclerView? = null
    //private lateinit var bottomNavigationView: AHBottomNavigation
    private var pb: ProgressBar? = null

    @ProvidePresenter
    fun provideRepositoryPresenter(): SearchContentPresenter {
        val navigationQualifier = requireNotNull(this.arguments?.getString(TAB_NAME))
        this.presenter.setNavigationQualifier(navigationQualifier)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
//        initBottomNavigation()
//        this.presenter.viewReady()
    }

//    private fun initBottomNavigation() {
//        this.bottomNavigationView = inner_bottom_navigation_view
//        AHBottomNavigationAdapter(activity, R.menu.inner_music_navigation).apply {
//            setupWithBottomNavigation(bottomNavigationView)
//
//        }
//        with(bottomNavigationView) {
//            this.accentColor = context.getColor(R.color.color_navigation_item_active)
//            this.inactiveColor = context.getColor(R.color.color_navigation_item_inactive)
//
//            this.setOnTabSelectedListener { position, wasSelected ->
//                if (!wasSelected) selectTab(
//                        when (position) {
//                            0 -> 1
//                            1 -> 2
//                            else -> 3
//                        }
//                )
//                true
//            }
//            val leftMargin = resources.getDimension(com.nimtego.plectrum.R.dimen.padding_medium).toInt()
//            this.setNotificationMarginLeft(leftMargin, leftMargin)
//        }

//        selectTab(
//                when (currentTabFragment?.tag) {
//                    BottomNavigationFragment.MUSIC_TAB.screenKey -> BottomNavigationFragment.MUSIC_TAB
//                    BottomNavigationFragment.MOVIE_TAB.screenKey -> BottomNavigationFragment.MOVIE_TAB
//                    BottomNavigationFragment.BOOK_TAB.screenKey -> BottomNavigationFragment.BOOK_TAB
//                    //todo remove
//                    else -> BottomNavigationFragment.MUSIC_TAB
//                }
//        )

//        this.bottomNavigationView.isBehaviorTranslationEnabled = false
//    }

    private fun selectTab(tab: Int) {
//        this.presenter.replaceFragment(tab)
        systemMessage(tab.toString())
    }

    override fun showProgress(show: Boolean) {
        pb?.visibility = if (show) ProgressBar.VISIBLE else ProgressBar.GONE
    }

    override fun onBackPressed(): Boolean {
        this.presenter.onBackPressed()
        return true
    }

    private fun init() {
        this.pb = view?.findViewById(R.id.pb_loading)
        this.searchContentRv = view?.findViewById(R.id.recycler_view_search_content)
        this.searchContentRv?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchContentFragment.context,
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

    override fun showViewState(data: List<ChildViewModel>) {
        //todo remove
        val tmpData = MusicTabModel("tmp", data)
        this.searchContentRv?.apply {
            adapter = MoreSectionAdapter(tmpData).apply {
                setOnItemClickListener(presenter)
            }
        }
    }

    companion object {
        fun getInstance(navigationQualifier: String): SearchContentFragment {
            val fragment = SearchContentFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TabName"
    }
}