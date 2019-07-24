package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.TabContentModel
import com.nimtego.plectrum.domain.interactor.PopularBookInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class BookTabPresenter @Inject constructor(
        private val tabContentRouter: Router,
        private val appRouter: Router,
        private val itemStorage: MainItemStorage,
        private val interactor: PopularBookInteractor
) : BasePresenter<TabContentView>(interactor), ParentTabAdapter.OnItemClickListener {

    private var tabContentModel: BaseParentViewModel<ChildViewModel>? = null


    fun viewIsReady(containerName: String) {
        tabContentModel?.let { showModel() }.run {
            interactor.execute(object : DisposableObserver<BaseParentViewModel<ChildViewModel>>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(tabContentModel: BaseParentViewModel<ChildViewModel>) {
                    Log.i("Presenter", "onnext")
                    this@BookTabPresenter.tabContentModel = tabContentModel
                    this@BookTabPresenter.showModel()
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@BottomNavigationPresenter.hideViewLoading()
//                this@BottomNavigationPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, PopularBookInteractor.Params.forRequest(containerName))

        }
    }

    private fun showModel() {
        this.tabContentModel?.let {
            this.viewState.showViewState(it)
        }
    }

    override fun sectionClicked(section: ParentTabModelContainer<ChildViewModel>) {
        this.itemStorage.changeCurrentSection(section)
        this.tabContentRouter.navigateTo(
                Screens.MoreContentScreen(NavigationQualifiers.TAB_BOOK_NAVIGATION)
        )
    }

    override fun childItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.tabContentRouter.navigateTo(
                Screens.ItemInformationScreen(NavigationQualifiers.TAB_BOOK_NAVIGATION)
        )
    }
}