package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.PopularMovieInteractor
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MovieTabPresenter @Inject constructor(
        private val tabContentRouter: Router,
        private val appRouter: Router,
        private val itemStorage: MainItemStorage,
        private val interactor: PopularMovieInteractor
) : BasePresenter<TabContentView>(), ParentTabAdapter.OnItemClickListener {

    override fun sectionClicked(section: ParentTabModelContainer<ChildViewModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun childItemClicked(childViewModel: ChildViewModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var movieModel: BaseParentViewModel<ChildViewModel>? = null


    fun viewIsReady(containerName: String) {
        movieModel?.let { showModel() }.run {
            interactor.execute(object : DisposableObserver<BaseParentViewModel<ChildViewModel>>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(tabContentModel: BaseParentViewModel<ChildViewModel>) {
                    Log.i("Presenter", "onnext")
                    this@MovieTabPresenter.movieModel = tabContentModel
                    this@MovieTabPresenter.showModel()
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@BottomNavigationPresenter.hideViewLoading()
//                this@BottomNavigationPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, PopularMovieInteractor.Params.forRequest(containerName))

        }
    }

    private fun showModel() {
        movieModel?.let {
            viewState.showViewState(it)
        }

    }
}