package com.nimtego.plectrum.presentation.main.artists

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.ArtistInteractorK
import com.nimtego.plectrum.presentation.base.BasePresenterK
import com.nimtego.plectrum.presentation.main.model.ArtistModelK
import io.reactivex.observers.DisposableObserver

@InjectViewState
class ArtistPresenterImpl(val interactor: ArtistInteractorK = ArtistInteractorK())
    : BasePresenterK<ArtistTabView>(), ArtistPresenterK {

    private val TAG = this.javaClass.canonicalName

    private var currentSearch = ""

    override fun search(response: String) {
//        if (view.getCurrentSerch() != response || view.isRvEmpty()) {
        currentSearch = response
        viewState.setCurrentSearch(response)
        viewState.clearList()
        showViewLoading()
        interactor.execute(object : DisposableObserver<List<ArtistModelK>>() {
            override fun onNext(dataModel: List<ArtistModelK>) {
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
        }, ArtistInteractorK.Params.forRequest(response))
//        }
    }

    override fun itemClick(artistModel: ArtistModelK) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun showViewLoading() {
        viewState.showProgress()
    }

    private fun hideViewLoading() {
        viewState.hideProgress()
    }

    private fun showArtistInView(artistModel: Collection<ArtistModelK>) {
        viewState.render(artistModel)
    }

}