package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.DashBoardSongsModel
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.DashBoardModelContainer
import com.nimtego.plectrum.presentation.mvp.view_model.dashboard.SectionViewModel
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.widget.adapters.DashBoardTabAdapter
import com.nimtego.plectrum.presentation.ui.widget.adapters.SectionChildAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router

@InjectViewState
class TabContentPresenter(
        router: Router,
        screenNumber: Int,
        private val interactor: TabContentInteractor
) : BasePresenter<TabContentView>(router, screenNumber), DashBoardTabAdapter.OnItemClickListener {

    override fun childItemClicked(id: String) {
        viewState.message(id)
    }



    override fun sectionClicked(sectionName: String) {
        router.navigateTo(Screens.MoreContentView(sectionName))
//        viewState.message(sectionName)
//        router.newRootScreen(Screens.MoreContentView(sectionName))
       // router.exit()
    }

    private var dataSongsModel: DashBoardSongsModel? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewIsReady()
    }

    fun viewIsReady() {
        dataSongsModel?.let { showModel(it) }.run {
            interactor.execute(object : DisposableObserver<DashBoardSongsModel>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(dataSongsModel: DashBoardSongsModel) {
                    Log.i("Presenter", "onnext")
                    this@TabContentPresenter.dataSongsModel = dataSongsModel
                    this@TabContentPresenter.showModel(dataSongsModel)
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@BottomNavigationPresenter.hideViewLoading()
//                this@BottomNavigationPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, TabContentInteractor.Params.forRequest(""))
        }
    }

    private fun showModel(dataSongsModel: DashBoardSongsModel) {
        //todo create res for title or...
        val data = BaseParentViewModel(listOf<DashBoardModelContainer<ChildViewModel>>(
                SectionViewModel("Top album", dataSongsModel.topAlbums),
                SectionViewModel("Top song", dataSongsModel.topSongs),
                SectionViewModel("Hot song", dataSongsModel.hotTrack),
                SectionViewModel("New music", dataSongsModel.newMusic))
        )

        viewState.showViewState(data)
    }

    fun albumClicked(albumModel: Album) {
        router.navigateTo(Screens.AlbumInformationDetail(albumModel.albumId.toString()))
    }

    fun songClicked(songModel: Song) {
        router.navigateTo(Screens.SongInformationDetail(songModel.trackId.toString()))
    }
}
