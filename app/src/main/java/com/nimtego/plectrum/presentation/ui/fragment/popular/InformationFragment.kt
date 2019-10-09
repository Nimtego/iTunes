package com.nimtego.plectrum.presentation.ui.fragment.popular

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import javax.inject.Inject
import android.widget.TextView
import android.widget.ImageView
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.general.InformationPresenter
import com.nimtego.plectrum.presentation.mvp.view.InformationView
import com.nimtego.plectrum.presentation.mvp.model.main_tab_model.ChildViewModel
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import javax.inject.Named


class InformationFragment : BaseFragment(), InformationView, BackButtonListener {

    override val layoutRes: Int = R.layout.track_information_fragment

    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    @InjectPresenter
    internal lateinit var presenter: InformationPresenter

    private var headTextView: TextView? = null
    private var minorHeadTextView: TextView? = null
    private var backgroundImageVIew: ImageView? = null
    private var contentImageVIew: ImageView? = null

    @ProvidePresenter
    fun provideRepositoryPresenter(): InformationPresenter {
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

    override fun showViewState(data: ChildViewModel) {
        this.headTextView?.text = data.mainName()
        this.minorHeadTextView?.text = data.minorName()

        Picasso.get().load(data.imageUrl().replace("100x100", "400x400"))
                .transform(BlurTransformation(this.context))
                .into(this.backgroundImageVIew, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception) {

                    }
                })
        Picasso.get().load(data.imageUrl().replace("100x100", "400x400"))
                .into(this.contentImageVIew, object : Callback {
                    override fun onSuccess() {

                    }

                    override fun onError(e: Exception) {

                    }
                })
    }

    companion object {
        fun getInstance(navigationQualifier: String): InformationFragment {
            val fragment = InformationFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TabName"
    }
}