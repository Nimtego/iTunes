package com.nimtego.plectrum.presentation.information_view.artist

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.base.BaseFragment
import com.nimtego.plectrum.presentation.base.BaseFragmentK
import com.nimtego.plectrum.presentation.information_view.artist.adapter.AlbumsAdapterForArtist
import com.nimtego.plectrum.presentation.information_view.artist.model.ArtistDetailsModelK
import com.nimtego.plectrum.presentation.utils.IpTagsK
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ArtistInformationFragmentK : BaseFragmentK(), ArtistInformationViewK {
    private var price: TextView? = null
    private var date: TextView? = null
    private var artistName: TextView? = null
    private var songs: TextView? = null
    private var albumImage: ImageView? = null
    private var information: TextView? = null
    private var pb: ProgressBar? = null
    private var albumsRv: RecyclerView? = null
    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    @InjectPresenter
    internal lateinit var presenter: ArtistInformationPresenterImpl

    @ProvidePresenter
    fun provideRepositoryPresenter(): ArtistInformationPresenterImpl {
        return ArtistInformationPresenterImpl()
    }


companion object {
    fun newInstance(content: String): ArtistInformationFragmentK {
        val fragment = ArtistInformationFragmentK()
        val arguments = Bundle()
        arguments.putString(IpTagsK.ARTIST_ID.name, content)
        (fragment as BaseFragment<*>).arguments = arguments
        return fragment
    }
}

//    override fun supplyPresenter(): ArtistInformationContract.Presenter<*, *> {
//        return ArtistInformationPresenter()
//    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.information_artist_form, container, false)
        artistName = view.findViewById(R.id.author)
        date = view.findViewById(R.id.release_date)
        price = view.findViewById(R.id.price)
        songs = view.findViewById(R.id.songs)
        information = view.findViewById(R.id.information)
        albumImage = view.findViewById(R.id.image_album)
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar)
        initRV(view, container)

        pb = view.findViewById(R.id.image_progress_bar)
        presenter.viewReady(arguments.getString(IpTagsK.ARTIST_ID.name))
        return view
    }

    protected fun initRV(view: View, viewGroup: ViewGroup?) {
        albumsRv = view.findViewById(R.id.album_rv)
        albumsRv!!.setHasFixedSize(true)
        albumsRv!!.layoutManager = LinearLayoutManager(viewGroup!!.context)
        ViewCompat.setNestedScrollingEnabled(albumsRv!!, false)
        albumsRv!!.itemAnimator = DefaultItemAnimator()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun render(artistDetailsModel: ArtistDetailsModelK) {
        artistName!!.text = artistDetailsModel.artistName
        price!!.text = artistDetailsModel.artistArtwork
        information!!.text = artistDetailsModel.wikiInformation
        collapsingToolbarLayout!!.title = artistDetailsModel.artistName
        val albumsAdapter = AlbumsAdapterForArtist(artistDetailsModel.albums,
                this.activity)
        albumsAdapter.setOnItemClickListener { albumModel -> presenter.albumClicked(albumModel) }
        albumsRv!!.adapter = albumsAdapter

        Picasso.get().load(artistDetailsModel.artistArtwork!!
                .replace("135x135", "570x570"))
                .into(albumImage!!, object : Callback {
                    override fun onSuccess() {
                        if (pb != null)
                            pb!!.visibility = View.GONE
                    }

                    override fun onError(e: Exception) {
                        if (pb != null)
                            pb!!.visibility = View.GONE
                    }
                })
    }
}