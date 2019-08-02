package com.nimtego.plectrum.presentation.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.TrackInformationInteractor
import com.nimtego.plectrum.presentation.manger.MusicalItemStorage
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import com.nimtego.plectrum.presentation.mvp.view.TrackInformationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class TrackInformationPresenter
@Inject constructor(
        private val tabContentRouter: Router,
        private val itemStorage: MusicalItemStorage,
        private val interactor: TrackInformationInteractor
) : BasePresenter<TrackInformationView>() {

    private var songModel: Song? = null

    private fun executeModel() {
        this.itemStorage.currentSong()?.let {
            this.interactor.getTrackById(TrackInformationInteractor.Params.forId(it))
                    .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        //addDisposable(observable.subscribeWith(TrackInformationObserver().connect()))
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        this.songModel?.let {
            showModel()
        } ?: run {
            executeModel()
        }
    }

    fun onBackPressed() {
        this.tabContentRouter.exit()
    }

    fun showModel() {
        this.songModel?.let {
            this.viewState.showViewState(it)
        }
    }

    private inner class TrackInformationObserver : DisposableObserver<Song>() {

        override fun onComplete() {}

        override fun onError(e: Throwable) {
//            this@UserDetailsPresenter.hideViewLoading()
//            this@UserDetailsPresenter.showErrorMessage(DefaultErrorBundle(e as Exception))
//            this@UserDetailsPresenter.showViewRetry()
        }

        override fun onNext(song: Song) {
            this@TrackInformationPresenter.songModel = song
            this@TrackInformationPresenter.showModel()
        }
    }

}