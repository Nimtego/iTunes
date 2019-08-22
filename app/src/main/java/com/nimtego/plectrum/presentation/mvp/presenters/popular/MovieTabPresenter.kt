package com.nimtego.plectrum.presentation.mvp.presenters.popular

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.popular.PopularMovieInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.presenters.base.BasePresenter
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.navigation.Screens
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

    private var movieModel: BaseParentViewModel<ChildViewModel>? = null


    fun viewIsReady(containerName: String) {
        movieModel?.let {
            showModel()
        } ?: run {
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
                }
            }, PopularMovieInteractor.Params.forRequest(containerName))

        }
    }

    private fun showModel() {
        movieModel?.let {
            viewState.showViewState(it)
        }
    }

    override fun sectionClicked(section: ParentTabModelContainer<ChildViewModel>) {
        this.itemStorage.changeCurrentSection(section)
        this.tabContentRouter.navigateTo(
                Screens.MoreContentScreen(NavigationQualifiers.TAB_MOVIE_NAVIGATION)
        )
    }

    override fun childItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.tabContentRouter.navigateTo(
                Screens.ItemInformationScreen(NavigationQualifiers.TAB_MOVIE_NAVIGATION)
        )
    }

    fun onBackPressed() {
        this.tabContentRouter.exit()
    }
}