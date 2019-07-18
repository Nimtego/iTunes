package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.InformationInteractor
import com.nimtego.plectrum.presentation.manger.ChildItemStorage
import com.nimtego.plectrum.presentation.mvp.view.InformationView
import com.nimtego.plectrum.presentation.mvp.model.information_view.SongDetailsModel
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class InformationPresenter
@Inject constructor(
        private val routerHandler: Map<String, Cicerone<Router>>,
        private val interactor: InformationInteractor,
        private val itemStorage: ChildItemStorage
) : BasePresenter<InformationView>(interactor) {

    private lateinit var navigationQualifier: String
    private var router: Router? = null
    private var dataSongsModel: SongDetailsModel? = null

    fun viewReady() {
        val item = this.itemStorage.getCurrentChildItem()?.mainName() ?: "Empty"
        this.viewState.systemMessage(item)
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

    private fun showModel(songs: SongDetailsModel) {
        viewState.showViewState(songs)
    }

    fun onBackPressed() {
        this.router?.exit()
    }

    fun setNavigationQualifier(navigationQualifier: String) {
        this.navigationQualifier = navigationQualifier
        this.router = routerHandler[navigationQualifier]?.router
    }

}