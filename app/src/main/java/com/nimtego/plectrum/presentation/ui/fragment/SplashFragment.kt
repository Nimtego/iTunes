package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.SplashPresenter
import com.nimtego.plectrum.presentation.mvp.view.BaseView
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Named

class SplashFragment : BaseFragment(), BaseView {

    override val layoutRes: Int = R.layout.splash_screen_fragment

    @field:[Inject Named(NavigationQualifiers.APP_NAVIGATION)]
    internal lateinit var appRouter: Router

    @Inject
    @InjectPresenter
    internal lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): SplashPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun showStatusBar() = false

    companion object {
        fun getInstance(): SplashFragment {
            val fragment = SplashFragment()
            return fragment
        }
    }

}