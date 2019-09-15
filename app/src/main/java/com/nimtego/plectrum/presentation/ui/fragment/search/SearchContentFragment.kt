package com.nimtego.plectrum.presentation.ui.fragment.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.song.MusicTabModel
import com.nimtego.plectrum.presentation.mvp.presenters.search.SearchContentPresenter
import com.nimtego.plectrum.presentation.mvp.view.SearchContentView
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.nimtego.plectrum.presentation.ui.widget.behavior.SpaceItemDecorator
import com.nimtego.plectrum.presentation.ui.widget.adapters.MoreSectionAdapter
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import javax.inject.Inject

class SearchContentFragment : BaseFragment(), SearchContentView, BackButtonListener {

    override val layoutRes: Int = R.layout.search_content_fragment

    @Inject
    @InjectPresenter
    internal lateinit var presenter: SearchContentPresenter

    private var searchContentRv: RecyclerView? = null
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