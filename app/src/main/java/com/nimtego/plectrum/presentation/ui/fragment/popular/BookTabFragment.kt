package com.nimtego.plectrum.presentation.ui.fragment.popular

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.presenters.popular.BookTabPresenter
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import javax.inject.Inject

class BookTabFragment : BaseTabFragment() {

    @Inject
    @InjectPresenter
    internal lateinit var presenter: BookTabPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): BookTabPresenter {
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

//Mark: view override

    override fun showViewState(data: BaseParentViewModel<ChildViewModel>) {
        this.parentContainerRecyclerView?.apply {
            adapter = ParentTabAdapter(data.sectionViewModel).apply {
                setOnItemClickListener(presenter)
            }
        }
    }

    companion object {
        fun getInstance(): BookTabFragment {
            val fragment = BookTabFragment()
            val arguments = Bundle()

            arguments.putString(TAB_NAME, TAB)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TAB_NAME"
        const val TAB = "BOOK_TAB"
    }
}