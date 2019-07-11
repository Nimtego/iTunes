package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.RouterProvider
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named
import android.support.design.widget.CollapsingToolbarLayout
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ImageView
import com.nimtego.plectrum.presentation.mvp.presenters.InformationPresenter
import com.nimtego.plectrum.presentation.mvp.view.InformationView
import com.nimtego.plectrum.presentation.mvp.model.information_view.SongDetailsModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class InformationFragment : BaseFragment(), InformationView, RouterProvider, BackButtonListener {

    override val layoutRes: Int = R.layout.information_fragment

    @field:[Inject Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)]
    internal lateinit var tabMusicRouter: Router

    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    internal lateinit var parentRouter: Router

    @Inject
    @InjectPresenter
    internal lateinit var presenter: InformationPresenter

    private var price: TextView? = null
    private var date: TextView? = null
    private var artistName: TextView? = null
    private var songs: TextView? = null
    private var albumImage: ImageView? = null
    private var information: TextView? = null
    private var pb: ProgressBar? = null
    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    @ProvidePresenter
    fun provideRepositoryPresenter(): InformationPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        this.presenter.viewReady()
    }

    override fun getRouter(): Router {
        return tabMusicRouter
    }

    override fun onBackPressed(): Boolean {
        tabMusicRouter.exit()
        return true
    }

    private fun init() {
        artistName = view?.findViewById(R.id.author);
        date = view?.findViewById(R.id.release_date);
        price = view?.findViewById(R.id.price);
        songs = view?.findViewById(R.id.songs);
        information = view?.findViewById(R.id.information);
        albumImage = view?.findViewById(R.id.image_album);
        collapsingToolbarLayout = view?.findViewById(R.id.collapsing_toolbar);

        pb = view?.findViewById(R.id.image_progress_bar);
    }

//Mark: view override

    override fun showViewState(data: SongDetailsModel) {
        artistName?.text = data.songArtistName
        date?.text = data.releaseDate
        price?.text = data.songPrice.toString()

        collapsingToolbarLayout?.title = data.songName
        Picasso.get().load(data.songArtwork
                ?.replace("100x100", "400x400"))
                .into(this.albumImage, object : Callback {
                    override fun onSuccess() {
                        pb?.visibility = View.GONE;
                    }

                    override fun onError(e: Exception) {
                        pb?.visibility = View.GONE;
                    }
                })
    }

    companion object {
        fun getInstance(): MovieTabFragment {
            val fragment = MovieTabFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, TAB)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TAB_NAME"
        const val TAB = "MOVIE_TAB"
    }
}