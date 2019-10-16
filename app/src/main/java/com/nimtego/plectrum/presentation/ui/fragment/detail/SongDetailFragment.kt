package com.nimtego.plectrum.presentation.ui.fragment.detail

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DefaultItemAnimator
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
import com.nimtego.plectrum.presentation.mvp.model.music.SongDetailModel
import com.nimtego.plectrum.presentation.mvp.presenters.detail.SongDetailPresenter
import com.nimtego.plectrum.presentation.mvp.view.detail.SongDetailView
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_song_fragment.*
import javax.inject.Inject
import javax.inject.Named

class SongDetailFragment: BaseFragment(), SongDetailView, BackButtonListener {

    override val layoutRes: Int = R.layout.detail_song_fragment

    private lateinit var price: TextView
    private lateinit var date: TextView
    private lateinit var artistName: TextView
    private lateinit var albumImage: ImageView
    private lateinit var information: TextView
    private lateinit var pb: ProgressBar
    private lateinit var albumsRv: RecyclerView
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    //todo create presenter and other
    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    @InjectPresenter
    internal lateinit var presenter: SongDetailPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): SongDetailPresenter {
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
        this.albumsRv = songs_album_rv
        albumsRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            ViewCompat.setNestedScrollingEnabled(this, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun showViewState(songDetailModel: SongDetailModel) {
        artistName.text = songDetailModel.trackName
        price.text = songDetailModel.trackPrice
        //information!!.setText(artistDetailsModel.getWikiInformation())
        collapsingToolbarLayout.title = songDetailModel.trackArtistName
//        val albumsAdapter = AlbumsAdapterForArtist(artistDetailsModel.getAlbums(),
//                this.getActivity())
//        albumsAdapter.setOnItemClickListener(object : AlbumsAdapterForArtist.OnItemClickListener() {
//            fun onUserItemClicked(albumDetailModel: AlbumDetailModel) {
//                mPresenter.albumClicked(albumDetailModel)
//            }
//        })
//        albumsRv.adapter = albumsAdapter

        Picasso.get().load(songDetailModel.trackArtwork
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
        fun getInstance(navigationQualifier: String): SongDetailFragment {
            val fragment = SongDetailFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TabName"
    }
}