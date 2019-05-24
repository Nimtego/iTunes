package com.nimtego.plectrum.presentation.main.artists

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nimtego.plectrum.presentation.main.adapter.SpacesItemDecoration
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragmentK
import com.nimtego.plectrum.presentation.main.model.AlbumModelK
import com.nimtego.plectrum.presentation.main.model.ArtistModelK
import java.util.*

class ArtistTabsFragmentK : MainTabsFragmentK(), ArtistTabView {

    @InjectPresenter
    internal lateinit var presenter: ArtistPresenterK

    override fun render(artistModels: Collection<AlbumModelK>) {
        val adapter = ArtistAdapterK(ArrayList(artistModels),
                this.activity)
        adapter.setOnItemClickListener( object : ArtistAdapterK.OnItemClickListener {
            override fun onUserItemClicked(artistModel: ArtistModelK) {
                presenter.itemClick(artistModel)
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

    companion object {

        fun getInstance(response: String): ArtistTabsFragment {
            val fragment = ArtistTabsFragment()
            val arguments = Bundle()
            //todo
//            arguments.putString(MainTabsFragmentK.RESPONSE, response)
            fragment.arguments = arguments
            return fragment
        }
    }
}
