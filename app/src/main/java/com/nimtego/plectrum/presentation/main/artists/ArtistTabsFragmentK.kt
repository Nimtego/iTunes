package com.nimtego.plectrum.presentation.main.artists

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.presentation.main.adapter.SpacesItemDecoration
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragmentK
import com.nimtego.plectrum.presentation.main.model.ArtistModel

class ArtistTabsFragmentK : MainTabsFragmentK(), ArtistTabView {

    @InjectPresenter
    internal lateinit var presenter: ArtistPresenterImpl

    @ProvidePresenter
    fun provideRepositoryPresenter(): ArtistPresenterImpl {
        return ArtistPresenterImpl()
    }

    override fun render(artistModels: Collection<ArtistModel>) {
        val adapter = ArtistAdapterK(ArrayList(artistModels),
                this.activity)
        adapter.setOnItemClickListener( object : ArtistAdapterK.OnItemClickListener {
            override fun onUserItemClicked(artistModel: ArtistModel) {
                presenter.itemClick(artistModel)
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

    companion object {

        fun getInstance(response: String): ArtistTabsFragmentK {
            val fragment = ArtistTabsFragmentK()
            val arguments = Bundle()
            arguments.putString(RESPONSE, response)
            fragment.arguments = arguments
            return fragment
        }
    }
}
