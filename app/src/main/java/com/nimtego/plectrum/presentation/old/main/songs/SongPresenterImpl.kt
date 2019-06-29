package com.nimtego.plectrum.presentation.old.main.songs

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.SongInteractor
import com.nimtego.plectrum.presentation.base.BasePresenter
import com.nimtego.plectrum.presentation.old.main.model.SongModel
import io.reactivex.observers.DisposableObserver

@InjectViewState
class SongPresenterImpl(val interactor: SongInteractor = SongInteractor())
    : BasePresenter<SongTabView>(), SongPresenter {
    override fun itemClick(artistModel: SongModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(response: String) {
        viewState.setCurrentSearch(response)
        viewState.clearList()
        showViewLoading()
        interactor.execute(object : DisposableObserver<List<SongModel>>() {
            override fun onNext(songModel: List<SongModel>) {
                this@SongPresenterImpl.showSongsInView(songModel)
            }

            override fun onError(e: Throwable) {
                this@SongPresenterImpl.hideViewLoading()
//                this@SongPresenterImpl.toast("error" + e.localizedMessage)
                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

            }

            override fun onComplete() {
                this@SongPresenterImpl.hideViewLoading()
            }
        }, SongInteractor.Params.forRequest(response))
    }

    private fun showViewLoading() {
        viewState.showProgress()
    }

    private fun hideViewLoading() {
        viewState.hideProgress()
    }

    private fun showSongsInView(songModel: Collection<SongModel>) {
        viewState.render(songModel)
    }
}