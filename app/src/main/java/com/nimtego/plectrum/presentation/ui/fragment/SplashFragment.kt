package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.widget.ProgressBar
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.SplashPresenter
import com.nimtego.plectrum.presentation.mvp.view.ProgressView
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class SplashFragment : BaseFragment(), ProgressView {

    override val layoutRes: Int = R.layout.splash_screen_fragment

    @field:[Inject Named(NavigationQualifiers.APP_NAVIGATION)]
    internal lateinit var appRouter: Router

    @Inject
    @InjectPresenter
    internal lateinit var presenter: SplashPresenter

    private var progressBar: ProgressBar? = null

    @ProvidePresenter
    fun provideRepositoryPresenter(): SplashPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.progressBar = view?.findViewById(R.id.image_progress_bar)
    }

    override fun showStatusBar() = false

    override fun showProgress(show: Boolean) {
        this.progressBar?.visibility = if (show) ProgressBar.VISIBLE else ProgressBar.INVISIBLE
    }

    companion object {
        fun getInstance(): SplashFragment {
            return SplashFragment()
        }
    }

}