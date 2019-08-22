package com.nimtego.plectrum.presentation.ui.fragment.general

import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.general.SplashPresenter
import com.nimtego.plectrum.presentation.mvp.view.ProgressView
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
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

    private var progressBar: LottieAnimationView? = null

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
        this.progressBar = view?.findViewById(R.id.launch_animation_lottie)
    }

    override fun showStatusBar() = false

    override fun showProgress(show: Boolean) {
        if (show) {
            this.progressBar?.playAnimation()
        }
        else {
            this.progressBar?.pauseAnimation()
        }
    }

    companion object {
        fun getInstance(): SplashFragment {
            return SplashFragment()
        }
    }

}