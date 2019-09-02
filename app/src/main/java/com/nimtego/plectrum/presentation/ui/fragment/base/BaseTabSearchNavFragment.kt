package com.nimtego.plectrum.presentation.ui.fragment.base

import android.os.Bundle
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.SearchTabNavPresenter
import com.nimtego.plectrum.presentation.mvp.view.SearchNavigationView
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

abstract class BaseTabSearchNavFragment : BaseFragment(), TabNavigationView, BackButtonListener {

    final override val layoutRes: Int = R.layout.navigation_container_fragment

    val layoutContainer: Int = R.id.navigation_layout_container

    abstract val navigatorHolder: NavigatorHolder

    abstract var presenter: SearchTabNavPresenter

    protected var navigator: Navigator? = null

    override fun onBackPressed(): Boolean {
        val fragment =
                this.childFragmentManager.findFragmentById(layoutContainer)
        return if (fragment is BackButtonListener) {
            fragment.onBackPressed()
        } else {
            this.presenter.onBackPressed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (this.navigator == null) {
            this.navigator = provideNavigator()
        }
    }

    abstract fun provideNavigator(): Navigator?

    override fun onResume() {
        super.onResume()
        this.navigatorHolder.setNavigator(this.navigator)
    }

    override fun onPause() {
        this.navigatorHolder.removeNavigator()
        super.onPause()
    }

    companion object {
        const val NAVIGATION_QUALIFIERS = "NavigationQualifiers"
    }
}