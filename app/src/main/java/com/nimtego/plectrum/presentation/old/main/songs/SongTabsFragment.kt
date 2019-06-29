package com.nimtego.plectrum.presentation.old.main.songs

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.presentation.old.main.fragments.MainTabsFragment
import com.nimtego.plectrum.presentation.old.main.model.SongModel
import com.nimtego.plectrum.presentation.ui.widget.SpaceItemDecorator

class SongTabsFragment : MainTabsFragment(), SongTabView {

    @InjectPresenter
    internal lateinit var presenter: SongPresenterImpl

    @ProvidePresenter
    fun provideRepositoryPresenter(): SongPresenterImpl {
        return SongPresenterImpl()
    }

    override fun render(songModels: Collection<SongModel>) {
        val adapter = SongAdapter(ArrayList(songModels),
                this.activity)
        adapter.setOnItemClickListener( object : SongAdapter.OnItemClickListener {
            override fun onUserItemClicked(songModel: SongModel) {
                presenter.itemClick(songModel)
            }
        })
        mRecyclerView?.adapter = adapter
    }

    override fun itemDecorator(): RecyclerView.ItemDecoration {
        return SpaceItemDecorator(1,
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

        fun getInstance(response: String): SongTabsFragment {
            val fragment =SongTabsFragment()
            val arguments = Bundle()
            arguments.putString(RESPONSE, response)
            fragment.arguments = arguments
            return fragment
        }
    }
}