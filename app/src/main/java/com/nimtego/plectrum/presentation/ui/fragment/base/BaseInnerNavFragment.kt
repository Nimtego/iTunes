package com.nimtego.plectrum.presentation.ui.fragment.base

import android.os.Bundle
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.TabNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder

abstract class BaseInnerNavFragment : BaseFragment(), TabNavigationView, BackButtonListener {

    final override val layoutRes: Int = R.layout.navigation_search_fragment

    val layoutContainer: Int = R.id.search_navigation_layout_container

    abstract var navigatorHolder: NavigatorHolder

    protected var navigator: Navigator? = null

    abstract var presenter: TabNavigationPresenter

    override fun onBackPressed(): Boolean {
        val fragment =
                this.childFragmentManager.findFragmentById(layoutContainer)

        return if (fragment is BackButtonListener) {
            fragment.onBackPressed()
        } else {
            return this.presenter.onBackPressed()
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
        this.presenter.viewIsVisible(true)
    }

    override fun onPause() {
        this.navigatorHolder.removeNavigator()
        this.presenter.viewIsVisible(false)
        super.onPause()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        this.presenter.viewIsVisible(!hidden)
    }

//    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
//        super.setUserVisibleHint(isVisibleToUser)
//        this.presenter.viewIsVisible(isVisibleToUser)
//    }
}