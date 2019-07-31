package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.InformationInteractor
import com.nimtego.plectrum.presentation.manger.MainItemStorage
import com.nimtego.plectrum.presentation.mvp.view.InformationView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class TrackInformationPresenter
@Inject constructor(
        private val tabContentRouter: Router,
        private val itemStorage: MainItemStorage,
        private val interactor: InformationInteractor
) : BasePresenter<InformationView>(interactor) {

    fun viewReady() {
//        this.itemStorage.getCurrentChildItem()?.let {
//            viewState.showViewState(it)
//        } ?: run{ viewState.systemMessage("NULL")}
//        this.itemStorage.getCurrentChildItem()?.let {
//            this.viewState.systemMessage(it.id())
//            interactor.execute(object : DisposableObserver<SongDetailsModel>() {
//                override fun onComplete() {
//                    Log.i("Presenter", "onComplete()")
//                }
//
//                override fun onNext(songs: SongDetailsModel) {
//                    Log.i("Presenter", "onnext")
//                    this@InformationPresenter.dataSongsModel = songs
//                    this@InformationPresenter.showModel(songs)
//                }
//
//                override fun onError(e: Throwable) {
//                    Log.i("Presenter", "onerror $e")
//                }
//            }, InformationInteractor.Params.forRequest(it.id()))
//        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.itemStorage.getCurrentChildItem()?.let {
            viewState.showViewState(it)
        } ?: run { viewState.systemMessage("NULL") }
    }

    fun onBackPressed() {
        this.tabContentRouter.exit()
    }

}