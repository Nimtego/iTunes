package com.nimtego.plectrum.presentation.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.nimtego.plectrum.data.entity.TabContentModel
import com.nimtego.plectrum.domain.interactor.TabContentInteractor
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.view.TabContentView
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.BaseParentViewModel
import com.nimtego.plectrum.presentation.mvp.view_model.main_tab_model.SectionViewModel
import com.nimtego.plectrum.presentation.ui.widget.adapters.ParentTabAdapter
import io.reactivex.observers.DisposableObserver
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

@InjectViewState
class MovieTabPresenter @Inject constructor(
        private val tabContentRouter: Router,
        private val appRouter: Router,
        private val interactor: TabContentInteractor
) : BasePresenter<TabContentView>(), ParentTabAdapter.OnItemClickListener {

    private var tabContentModel: TabContentModel? = null

    override fun sectionClicked(sectionName: String) {

    }

    override fun childItemClicked(id: String) {

    }

    fun viewIsReady(containerName: String) {
        tabContentModel?.let { showModel(it) }.run {
            interactor.execute(object : DisposableObserver<TabContentModel>() {
                override fun onComplete() {
                    Log.i("Presenter", "onComplete()")
                }

                override fun onNext(tabContentModel: TabContentModel) {
                    Log.i("Presenter", "onnext")
                    this@MovieTabPresenter.tabContentModel = tabContentModel
                    this@MovieTabPresenter.showModel(tabContentModel)
                }

                override fun onError(e: Throwable) {
                    Log.i("Presenter", "onerror $e")
//                this@BottomNavigationPresenter.hideViewLoading()
//                this@BottomNavigationPresenter.toast("error" + e.localizedMessage)
//                // TODO: 01.11.2018 retry  view (showRetry() + hideRetry() in contract);

                }
            }, TabContentInteractor.Params.forRequest(containerName))

        }
    }

    private fun showModel(tabContentModel: TabContentModel) {
        //todo create res for title or...
        val listContent = tabContentModel.contentList
        val data = BaseParentViewModel(listContent.map {
            SectionViewModel(it.title(), it.getModels())
        })
        viewState.showViewState(data)

    }
}