package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import com.nimtego.plectrum.domain.interactor.MoreSectionInteractor
import com.nimtego.plectrum.presentation.manger.SectionItemStorage
import com.nimtego.plectrum.presentation.mvp.view.MoreSectionView
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MoreSectionPresenter
@Inject constructor(
        private val musicTabRouter: Router,
        private val interactor: MoreSectionInteractor,
        private val itemStorage: SectionItemStorage
) : BasePresenter<MoreSectionView>() {

    private var dataSongsModel: List<Song>? = null

    fun viewReady() {
        this.itemStorage.getCurrentSection()?.let {
            this.viewState.systemMessage(it.title())
            interactor.execute(object : DisposableObserver<List<Song>>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(songs: List<Song>) {
                    Log.i("Presenter", "onnext")
                    this@MoreSectionPresenter.dataSongsModel = songs
                    this@MoreSectionPresenter.showModel(songs)
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
                }
            }, MoreSectionInteractor.Params.forRequest(it.title()))
        }
    }

    private fun showModel(songs: List<Song>) {

        viewState.showViewState(songs)
    }

    fun onBackPressed() {
        this.musicTabRouter.exit()
    }
}