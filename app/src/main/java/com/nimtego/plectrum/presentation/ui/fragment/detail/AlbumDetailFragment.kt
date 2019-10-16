package com.nimtego.plectrum.presentation.ui.fragment.detail

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel
import com.nimtego.plectrum.presentation.mvp.presenters.detail.AlbumDetailPresenter
import com.nimtego.plectrum.presentation.mvp.view.detail.AlbumDetailView
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.nimtego.plectrum.presentation.ui.widget.adapters.detail.SongsDetailAdapter
import com.nimtego.plectrum.presentation.ui.widget.util.Util
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_song_fragment.*
import java.lang.StringBuilder
import javax.inject.Inject
import javax.inject.Named

class AlbumDetailFragment : BaseFragment(), AlbumDetailView, BackButtonListener {

    override val layoutRes: Int = R.layout.detail_album_fragment

    private lateinit var price: TextView
    private lateinit var date: TextView
    private lateinit var artistName: TextView
    private lateinit var albumImage: ImageView
    private lateinit var information: TextView
    private lateinit var pb: ProgressBar
    private lateinit var songsRv: RecyclerView
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    //todo create presenter and other
    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    @InjectPresenter
    internal lateinit var presenter: AlbumDetailPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): AlbumDetailPresenter {
        val navigationQualifier = requireNotNull(this.arguments?.getString(TAB_NAME))
        this.presenter.setNavigationQualifier(navigationQualifier)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    override fun showProgress(show: Boolean) {
        if(show)pb.visibility = View.VISIBLE else  pb.visibility = View.GONE
    }

    override fun onBackPressed(): Boolean {
        this.presenter.onBackPressed()
        return true
    }

    private fun init() {
        this.artistName = author_value
        this.date = release_date_value
        this.price = price_value
        this.information = information_value
        this.albumImage = image_album
        this.collapsingToolbarLayout = collapsing_toolbar
        this.pb = image_progress_bar

        initAlbumsListRV()
    }

    private fun initAlbumsListRV() {
        val itemInColumn = Util.calculateNoOfColumns(
                this@AlbumDetailFragment.context,
                100F + 10)
        this.songsRv = songs_album_rv
        songsRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@AlbumDetailFragment.context)
//            addItemDecoration(SpaceItemDecorator(spacing = 4,
//                    spanCount = 1,
//                    paddingTop = 24,
//                    paddingBottom = 34))
            isNestedScrollingEnabled = false
        }
    }

    override fun showViewState(albumDetailModel: AlbumDetailModel) {
        artistName.text = albumDetailModel.albumArtistName
        //price.setText(data.getArtistArtwork())
        val track = StringBuilder()
        albumDetailModel.albumSongDetails.forEach {
            track.append(it.trackName)
            track.append("/n")
        }
        information.text = track
        collapsingToolbarLayout.title = albumDetailModel.albumName
        val songsAdapter = SongsDetailAdapter(albumDetailModel.albumSongDetails)
        songsAdapter.setOnItemClickListener(object: SongsDetailAdapter.OnItemClickListener {
            override fun onUserItemClicked(songDetailModel: SongDetailModel) {
                presenter.songClicked(songDetailModel)
            }
        })
        songsRv.adapter = songsAdapter

        Picasso.get().load(albumDetailModel.albumArtwork
                .replace("100x100", "570x570"))
                .into(albumImage, object : Callback {
                    override fun onSuccess() {
                        pb.visibility = View.GONE
                    }

                    override fun onError(e: Exception) {
                        pb.visibility = View.GONE
                    }
                })
    }

    companion object {
        fun getInstance(navigationQualifier: String): AlbumDetailFragment {
            val fragment = AlbumDetailFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TabName"
    }
}