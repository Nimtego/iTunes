package com.nimtego.plectrum.presentation.main.albums

import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.domain.interactor.AlbumInteractor
import com.nimtego.plectrum.domain.interactor.AlbumInteractorK
import com.nimtego.plectrum.presentation.base.BasePresenterK
import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import com.nimtego.plectrum.presentation.utils.FragmentTypeK
import io.reactivex.observers.DisposableObserver
import java.util.*

@InjectViewState
class AlbumPresenterImpl(val interactor: AlbumInteractorK = AlbumInteractorK())
    : BasePresenterK<AlbumTabView>(), AlbumPresenterK {

    private val TAG = this.javaClass.canonicalName

    private var currentSearch = ""

//    @Inject
//    constructor() : this(AlbumInteractor()) {
//        // TODO: 29.10.2018 replaceable di
//    }

    override fun albumClicked(albumModel: AlbumModelK) {
        val param = HashMap<String, String>()
        param[FragmentTypeK.TYPE.name] = FragmentTypeK.ALBUM.name
        //todo
//        param[IpTagsK.ALBUM_ID.name] = albumModel.albumId
//        try {
//            view.showView(DetailedInformationContract.View<*>::class.java, param)
//        } catch (e: IllegalArgumentException) {
//            view.toast(e.message)
//        }

    }

    override fun search(response: String) {
        //todo
        //if (viewState.currentSerch != response || view.isRvEmpty) {
            viewState.setCurrentSearch(response)
            viewState.clearList()
            showViewLoading()
            interactor.execute(object : DisposableObserver<List<AlbumModelK>>() {
                override fun onNext(dataModel: List<AlbumModelK>) {
                    this@AlbumPresenterImpl.showAlbumsInView(dataModel)
                }

                override fun onError(e: Throwable) {
                    this@AlbumPresenterImpl.hideViewLoading()
                    this@AlbumPresenterImpl.toast("error" + e.localizedMessage)
                    // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }

                override fun onComplete() {
                    this@AlbumPresenterImpl.hideViewLoading()
                }
            }, AlbumInteractorK.Params.forRequest(response))
//        }
    }

    private fun showViewLoading() {
        viewState.showProgress()
    }

    private fun showAlbumsInView(albumModels: Collection<AlbumModelK>) {
        viewState.render(albumModels)
    }

    private fun hideViewLoading() {
        viewState.hideProgress()
    }

    private fun toast(message: String) {
        //todo
//        viewState.toast(message)
    }
}