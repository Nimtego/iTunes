package com.nimtego.plectrum.presentation.migrate_kotlin.ui

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.R
import com.nimtego.plectrum.data.entity.Album
import com.nimtego.plectrum.data.entity.DashBoardModel
import com.nimtego.plectrum.data.entity.Song
import com.nimtego.plectrum.domain.interactor.DashBoardInteractor
import com.nimtego.plectrum.presentation.main.adapter.SpacesItemDecoration
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.DashBoardView
import com.nimtego.plectrum.presentation.migrate_kotlin.presenters.DashboardPresenter
import com.nimtego.plectrum.presentation.migrate_kotlin.view_model.TopAlbumAdapter
import com.nimtego.plectrum.presentation.migrate_kotlin.view_model.TopSongAdapter
import ru.terrakok.cicerone.Router
import java.util.*
import android.support.v7.widget.DividerItemDecoration
import com.nimtego.plectrum.presentation.main.adapter.SpaceItemDecorator


class DashboardFragment : MvpAppCompatFragment(), DashBoardView {

    private var topSongRecyclerView: RecyclerView? = null
    private var topAlbumRecyclerView: RecyclerView? = null
    private var newMediaRecyclerView: RecyclerView? = null
    private var hotMediaRecyclerView: RecyclerView? = null

    @InjectPresenter
    internal lateinit var presenter: DashboardPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): DashboardPresenter {
        return DashboardPresenter(Router(),5, DashBoardInteractor())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dashboard_fragment, container, false)
        initRV(view, container)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.viewIsReady()
    }

    protected fun initRV(view: View, viewGroup: ViewGroup?) {
        //todo refac
        this.topSongRecyclerView = view.findViewById(R.id.recycler_view_top_songs)
        this.topAlbumRecyclerView = view.findViewById(R.id.recycler_view_top_albums)
        this.newMediaRecyclerView = view.findViewById(R.id.recycler_view_new_media)
        this.hotMediaRecyclerView = view.findViewById(R.id.recycler_view_hot_media)

        this.topSongRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DashboardFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false)
            addItemDecoration(SpaceItemDecorator(spacing = 30))
//            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.HORIZONTAL))
            itemAnimator = DefaultItemAnimator()
        }
        this.topAlbumRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DashboardFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false)
            addItemDecoration(SpaceItemDecorator(spacing = 30))
            itemAnimator = DefaultItemAnimator()
        }
        this.newMediaRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DashboardFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false)
            addItemDecoration(SpaceItemDecorator(spacing = 30))
            itemAnimator = DefaultItemAnimator()
        }

        this.hotMediaRecyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DashboardFragment.context,
                    LinearLayoutManager.HORIZONTAL,
                    false)
            addItemDecoration(SpaceItemDecorator(spacing = 30))
            itemAnimator = DefaultItemAnimator()
        }
    }
    override fun showViewState(dashboardModel: DashBoardModel) {
        val adapterTopAlbum = TopAlbumAdapter(ArrayList(dashboardModel.topAlbums),
                this.activity)
        adapterTopAlbum.setOnItemClickListener( object : TopAlbumAdapter.OnItemClickListener {
            override fun onUserItemClicked(albumModel: Album) {
                presenter.albumClicked(albumModel)
            }
        })

        val adapterTopSong = TopSongAdapter(ArrayList(dashboardModel.topSongs),
                this.activity)
        adapterTopSong.setOnItemClickListener( object : TopSongAdapter.OnItemClickListener {
            override fun onUserItemClicked(songModel: Song) {
                presenter.songClicked(songModel)
            }
        })

        val adapterNewSong = TopSongAdapter(ArrayList(dashboardModel.newMusic),
                this.activity)
        adapterTopSong.setOnItemClickListener( object : TopSongAdapter.OnItemClickListener {
            override fun onUserItemClicked(songModel: Song) {
                presenter.songClicked(songModel)
            }
        })

        val adapterHotSong = TopSongAdapter(ArrayList(dashboardModel.hotTrack),
                this.activity)
        adapterTopSong.setOnItemClickListener( object : TopSongAdapter.OnItemClickListener {
            override fun onUserItemClicked(songModel: Song) {
                presenter.songClicked(songModel)
            }
        })

        topAlbumRecyclerView?.adapter = adapterTopAlbum

        topSongRecyclerView?.adapter = adapterTopSong

        newMediaRecyclerView?.adapter = adapterNewSong

        hotMediaRecyclerView?.adapter = adapterHotSong

    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun getInstance() = DashboardFragment()
    }
}