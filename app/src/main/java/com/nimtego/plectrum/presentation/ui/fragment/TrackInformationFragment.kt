package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import com.nimtego.plectrum.presentation.mvp.presenters.InformationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.TrackInformationPresenter
import com.nimtego.plectrum.presentation.mvp.view.InformationView
import com.nimtego.plectrum.presentation.mvp.view.TrackInformationView
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import javax.inject.Inject

class TrackInformationFragment : BaseFragment(), TrackInformationView, BackButtonListener {

    override val layoutRes: Int = R.layout.track_information_fragment

    @Inject
    @InjectPresenter
    internal lateinit var presenter: TrackInformationPresenter

    private var headTextView: TextView? = null
    private var minorHeadTextView: TextView? = null
    private var backgroundImageVIew: ImageView? = null
    private var contentImageVIew: ImageView? = null

    @ProvidePresenter
    fun provideRepositoryPresenter(): TrackInformationPresenter {
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

    override fun onBackPressed(): Boolean {
        this.presenter.onBackPressed()
        return true
    }

    private fun init() {
        this.headTextView = view?.findViewById(R.id.head_main_text_view)
        this.minorHeadTextView = view?.findViewById(R.id.minor_head_text_view)
        this.backgroundImageVIew = view?.findViewById(R.id.information_background_image_view)
        this.contentImageVIew = view?.findViewById(R.id.content_image_view)
    }

//Mark: view override

    override fun showViewState(data: Song) {
        this.headTextView?.text = data.trackName
        this.minorHeadTextView?.text = data.artistName

        Picasso.get().load(data.trackArtWorkUrl.replace("100x100", "400x400"))
                .transform(BlurTransformation(this.context))
                .into(this.backgroundImageVIew, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception) {

                    }
                })
        Picasso.get().load(data.trackArtWorkUrl.replace("100x100", "400x400"))
                .into(this.contentImageVIew, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception) {

                    }
                })
    }

    companion object {
        fun getInstance(navigationQualifier: String): TrackInformationFragment {
            val fragment = TrackInformationFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TabName"
    }
}