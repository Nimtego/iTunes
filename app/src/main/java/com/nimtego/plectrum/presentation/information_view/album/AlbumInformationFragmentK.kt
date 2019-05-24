package com.nimtego.plectrum.presentation.information_view.album

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
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
import com.nimtego.plectrum.presentation.information_view.album.model.AlbumDetailsModelK
import com.nimtego.plectrum.presentation.utils.IpTagsK
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class AlbumInformationFragmentK : BaseFragmentK(), AlbumInformationViewK {
    private var price: TextView? = null
    private var date: TextView? = null
    private var artistName: TextView? = null
    private var songs: TextView? = null
    private var albumImage: ImageView? = null
    private var information: TextView? = null
    private var pb: ProgressBar? = null
    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    @InjectPresenter
    internal lateinit var presenter: AlbumInformationPresenterImpl

    @ProvidePresenter
    fun provideRepositoryPresenter(): AlbumInformationPresenterImpl {
        return AlbumInformationPresenterImpl()
    }
    companion object {
        fun newInstance(content: String): AlbumInformationFragmentK {
            val fragment = AlbumInformationFragmentK()
            val arguments = Bundle()
            arguments.putString(IpTagsK.ALBUM_ID.name, content)
            (fragment as BaseFragment<*>).arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.information_album_form, container, false)
        artistName = view.findViewById(R.id.author)
        date = view.findViewById(R.id.release_date)
        price = view.findViewById(R.id.price)
        songs = view.findViewById(R.id.songs)
        information = view.findViewById(R.id.information)
        albumImage = view.findViewById(R.id.image_album)
        collapsingToolbarLayout = view.findViewById(R.id.collapsing_toolbar)

        pb = view.findViewById(R.id.image_progress_bar)
        presenter.viewReady(arguments.getString(IpTagsK.ALBUM_ID.name))
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun render(albumDetailsModel: AlbumDetailsModelK) {
        artistName!!.text = albumDetailsModel.albumArtistName
        date!!.text = albumDetailsModel.releaseDate
        price!!.text = albumDetailsModel.collectionPrice.toString()
        information!!.text = albumDetailsModel.wikiInformation
        val sb = StringBuilder()
        albumDetailsModel.songs!!.forEach { (trackName) -> sb.append(trackName).append("\n\n") }
        songs!!.text = sb
        collapsingToolbarLayout!!.title = albumDetailsModel.albumName
        Picasso.get().load(albumDetailsModel.albumArtwork!!
                .replace("100x100", "400x400"))
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