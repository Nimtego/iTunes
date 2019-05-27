package com.nimtego.plectrum.presentation.migrate_kotlin.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.DashBoardModel
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.DashBoardView
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router


@InjectViewState
class DashboardPresenter(private val router: Router?,
                         private val screenNumber: Int,
                         private val interactor: DashBoardInteractor)
    : BasePresenter<DashBoardView>(router, screenNumber) {

    override fun attachView(view: DashBoardView) {
        super.attachView(view)
        interactor.execute(object : DisposableObserver<DashBoardModel>() {
            override fun onComplete() {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(dataModel: DashBoardModel) {
                this@DashboardPresenter.showModel(dataModel)
            }

            override fun onError(e: Throwable) {
//                this@DashboardPresenter.hideViewLoading()
//                this@DashboardPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

            }
        }, DashBoardInteractor.Params.forRequest(""))

    }

    private fun showModel(dataModel: DashBoardModel) {
        viewState.showViewState(dataModel)
    }

    fun albumClicked(albumModel: Album) {
        //todo
    }

    fun songClicked(albumModel: Song) {
        //todo
    }

}