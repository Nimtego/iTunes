package com.nimtego.plectrum.presentation.main.songs

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.presentation.main.adapter.SpacesItemDecoration
import com.nimtego.plectrum.presentation.main.albums.AlbumPresenterImpl
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragmentK
import com.nimtego.plectrum.presentation.main.model.SongModelK

class SongTabsFragmentK : MainTabsFragmentK(), SongTabView {

    @InjectPresenter
    internal lateinit var presenter: SongPresenterImpl

    @ProvidePresenter
    fun provideRepositoryPresenter(): SongPresenterImpl {
        return SongPresenterImpl()
    }

    override fun render(songModels: Collection<SongModelK>) {
        val adapter = SongAdapterK(ArrayList(songModels),
                this.activity)
        adapter.setOnItemClickListener( object : SongAdapterK.OnItemClickListener {
            override fun onUserItemClicked(songModel: SongModelK) {
                presenter.itemClick(songModel)
            }
        })
        mRecyclerView?.adapter = adapter
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun itemDecorator(): RecyclerView.ItemDecoration {
        return SpacesItemDecoration(1,
                20,
                true)
    }

    override fun rvLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun search(response: String) {
        presenter.search(response)
    }

    companion object {

        fun getInstance(response: String): SongTabsFragmentK {
            val fragment =SongTabsFragmentK()
            val arguments = Bundle()
            arguments.putString(RESPONSE, response)
            fragment.arguments = arguments
            return fragment
        }
    }
}