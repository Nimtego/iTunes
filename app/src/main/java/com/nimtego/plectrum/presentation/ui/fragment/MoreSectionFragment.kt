package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
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
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.presentation.mvp.presenters.MoreSectionPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.RouterProvider
import com.nimtego.plectrum.presentation.mvp.view.MoreSectionView
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import com.nimtego.plectrum.presentation.ui.widget.adapters.SongAdapter
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import com.nimtego.plectrum.presentation.ui.widget.toast.SimpleToastAlarm
import javax.inject.Inject

class MoreSectionFragment : MvpAppCompatFragment(), MoreSectionView, BackButtonListener {
    override fun message(message: String) {
        SimpleToastAlarm(this.context).message(message)
    }

    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()
        return true
    }

    private var parentContainerRecyclerView: RecyclerView? = null

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
        presenter.router = (this.parentFragment as RouterProvider).getRouter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.more_section_fragment, container, false)
        initRV(view, container)
        presenter.viewReady(arguments.getString("SECTION"))
        return view
    }

    protected fun initRV(view: View, viewGroup: ViewGroup?) {
        this.parentContainerRecyclerView = view.findViewById(R.id.recycler_view_more_section)

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
            arguments.putString("SECTION", sectionName)
            fragment.arguments = arguments
            return fragment
        }
    }
}