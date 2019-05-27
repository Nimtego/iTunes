package com.nimtego.plectrum.presentation.main.artists

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.ArtistInteractor
import com.nimtego.plectrum.presentation.base.BasePresenter
import com.nimtego.plectrum.presentation.main.model.ArtistModel
import io.reactivex.observers.DisposableObserver

@InjectViewState
class ArtistPresenterImpl(val interactor: ArtistInteractor = ArtistInteractor())
    : BasePresenter<ArtistTabView>(), ArtistPresenter {

    private val TAG = this.javaClass.canonicalName

    private var currentSearch = ""

    override fun search(response: String) {
//        if (view.getCurrentSerch() != response || view.isRvEmpty()) {
        currentSearch = response
        viewState.setCurrentSearch(response)
        viewState.clearList()
        showViewLoading()
        interactor.execute(object : DisposableObserver<List<ArtistModel>>() {
            override fun onNext(dataModel: List<ArtistModel>) {
                this@ArtistPresenterImpl.showArtistInView(dataModel)
            }

            override fun onError(e: Throwable) {
                this@ArtistPresenterImpl.hideViewLoading()
//                    this@ArtistPresenterImpl.toast("error" + e.localizedMessage)
                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

            }

            override fun onComplete() {
                this@ArtistPresenterImpl.hideViewLoading()
            }
        }, ArtistInteractor.Params.forRequest(response))
//        }
    }

    override fun itemClick(artistModel: ArtistModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showViewLoading() {
        viewState.showProgress()
    }

    private fun hideViewLoading() {
        viewState.hideProgress()
    }

    private fun showArtistInView(artistModel: Collection<ArtistModel>) {
        viewState.render(artistModel)
    }

}