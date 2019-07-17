package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.MoreSectionInteractor
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.manger.SectionItemStorage
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ParentTabModelContainer
import com.nimtego.plectrum.presentation.mvp.view.MoreSectionView
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.MoreSectionAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MoreSectionPresenter
@Inject constructor(
        private val musicTabRouter: Router,
        private val interactor: MoreSectionInteractor,
        private val itemStorage: MainItemStorage
) : BasePresenter<MoreSectionView>(interactor), MoreSectionAdapter.OnItemClickListener {

    override fun onUserItemClicked(childViewModel: ChildViewModel) {
        this.itemStorage.changeCurrentChildItem(childViewModel)
        this.musicTabRouter.navigateTo(Screens.ItemInformationScreen)
    }

    private var dataModel: ParentTabModelContainer<ChildViewModel>? = null

    override fun attachView(view: MoreSectionView) {
        super.attachView(view)
        viewReady()
    }

    fun viewReady() {
        this.dataModel?.let { showModel() } ?: this.itemStorage.getCurrentSection()?.let {
            this.viewState.systemMessage(it.title())
            this.dataModel = it
            showModel()
        } ?: run {
            interactor.execute(object : DisposableObserver<ParentTabModelContainer<ChildViewModel>>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(songs: ParentTabModelContainer<ChildViewModel>) {
                    Log.i("Presenter", "onnext")
                    this@MoreSectionPresenter.dataModel = songs
                    this@MoreSectionPresenter.showModel()
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
                }
            }, MoreSectionInteractor.Params.forRequest("HOT_TRACK"))

        }
    }

    private fun showModel() {
        dataModel?.let { viewState.showViewState(it) }
    }

    fun onBackPressed() {
        this.musicTabRouter.exit()
    }
}