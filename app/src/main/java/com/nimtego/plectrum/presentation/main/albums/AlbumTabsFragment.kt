package com.nimtego.plectrum.presentation.main.albums

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragment
import com.nimtego.plectrum.presentation.main.model.AlbumModel
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator
import java.util.*

class AlbumTabsFragment : MainTabsFragment(), AlbumTabView {

    @InjectPresenter
    internal lateinit var presenter: AlbumPresenterImpl

    @ProvidePresenter
    fun provideRepositoryPresenter(): AlbumPresenterImpl {
        return AlbumPresenterImpl()
    }

    override fun render(albumModel: Collection<AlbumModel>) {
        val adapter = AlbumAdapter(ArrayList(albumModel),
                this.activity)
        adapter.setOnItemClickListener( object : AlbumAdapter.OnItemClickListener {
            override fun onUserItemClicked(albumModel: AlbumModel) {
                presenter.albumClicked(albumModel)
            }
        })
        mRecyclerView?.adapter = adapter
    }

    override fun itemDecorator(): RecyclerView.ItemDecoration {
        return SpaceItemDecorator(2,
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


        fun getInstance(response: String): AlbumTabsFragment {
            val fragment = AlbumTabsFragment()
            val arguments = Bundle()
            arguments.putString(RESPONSE, response)
            fragment.arguments = arguments
            return fragment
        }
    }
}