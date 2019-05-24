package com.nimtego.plectrum.presentation.main.albums

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nimtego.plectrum.presentation.main.adapter.SpacesItemDecoration
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragmentK
import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import java.util.*

class AlbumTabsFragmentK : MainTabsFragmentK(), AlbumTabView {

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @InjectPresenter
    internal lateinit var presenter: AlbumPresenterK

    override fun render(albumModel: Collection<AlbumModelK>) {
        val adapter = AlbumAdapterK(ArrayList(albumModel),
                this.activity)
        adapter.setOnItemClickListener( object : AlbumAdapterK.OnItemClickListener {
            override fun onUserItemClicked(albumModel: AlbumModelK) {
                presenter.albumClicked(albumModel)
            }
        })
        mRecyclerView?.adapter = adapter
    }

    override fun itemDecorator(): RecyclerView.ItemDecoration {
        return SpacesItemDecoration(2,
                30,
                true)
    }

    override fun rvLayoutManager(context: Context): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 2)
    }


    override fun search(response: String) {
        presenter.search(response)
    }

//    override fun supplyPresenter(): AlbumContract.Presenter<*, *> {
//        return App.getComponent().mainAlbumPresenter()
//    }

    companion object {


        fun getInstance(response: String): AlbumTabsFragmentK {
            val fragment = AlbumTabsFragmentK()
            val arguments = Bundle()
            //todo
            /*arguments.putString(MainTabsFragmentK.RESPONSE, response)*/
            fragment.arguments = arguments
            return fragment
        }
    }
}