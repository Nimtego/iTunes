package com.nimtego.plectrum.presentation.ui.fragment.popular

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.presenters.general.MoreSectionPresenter
import com.nimtego.plectrum.presentation.mvp.view.MoreSectionView
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.nimtego.plectrum.presentation.ui.widget.behavior.SpaceItemDecorator
import com.nimtego.plectrum.presentation.ui.widget.adapters.MoreSectionAdapter
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import javax.inject.Inject

class MoreSectionFragment : BaseFragment(), MoreSectionView, BackButtonListener {

    override val layoutRes: Int = R.layout.more_section_fragment

    private var parentContainerRecyclerView: RecyclerView? = null

    @Inject
    @InjectPresenter
    internal lateinit var presenter: MoreSectionPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): MoreSectionPresenter {
        val navigationQualifier = requireNotNull(this.arguments?.getString(TAB_NAME))
        this.presenter.setNavigationQualifier(navigationQualifier)
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
        presenter.prepareViewModel()
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

    override fun showViewState(data: ParentTabModelContainer<ChildViewModel>) {
        this.parentContainerRecyclerView?.apply {
            adapter = MoreSectionAdapter(data).apply {
                setOnItemClickListener(presenter)
            }
        }
    }

    companion object {
        fun getInstance(qualifier: String): MoreSectionFragment {
            val fragment = MoreSectionFragment()
            val arguments = Bundle()
            arguments.putString(TAB_NAME, qualifier)
            fragment.arguments = arguments
            return fragment
        }

        const val TAB_NAME = "TabName"
    }
}