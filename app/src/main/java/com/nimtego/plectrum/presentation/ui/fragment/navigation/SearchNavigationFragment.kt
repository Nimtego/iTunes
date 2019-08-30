package com.nimtego.plectrum.presentation.ui.fragment.navigation

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.SearchNavigationPresenter
import com.nimtego.plectrum.presentation.navigation.NavigationHandler
import com.nimtego.plectrum.presentation.navigation.ScreenTabContainer
import com.nimtego.plectrum.presentation.navigation.SearchTabScreenFabric
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseSearchNavFragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject
import javax.inject.Named

class SearchNavigationFragment : BaseSearchNavFragment() {

    @field:[Inject Named(NavigationQualifiers.SEARCH_NAVIGATION_ROUTER_HANDLER)]
    internal lateinit var searchNavigationHandler: NavigationHandler

    @Inject
    internal lateinit var searchTabScreenFabric: SearchTabScreenFabric

    @field:[Inject Named(NavigationQualifiers.SEARCH_NAVIGATION)]
    @InjectPresenter
    override lateinit var presenter: SearchNavigationPresenter

    override val navigatorHolder: NavigatorHolder by lazy {
        this.searchNavigationHandler.getNavigatorHolder(navigationQualifier)
    }

    private val navigationQualifier: String
        get() = requireNotNull(this.arguments?.getString(NAVIGATION_QUALIFIERS))

    @ProvidePresenter
    fun provideRepositoryPresenter(): SearchNavigationPresenter {
        this.presenter.setNavigationQualifiers(navigationQualifier)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun provideNavigator(): Navigator? {
        return context?.let {
            SearchNavigator(childFragmentManager,
                    this.searchTabScreenFabric.getScreensContainer(this.navigationQualifier),
                    it as AppCompatActivity,
                    this.layoutContainer)
        }
    }

// MARK: - Inner Types

    private inner class SearchNavigator(
            private val fragmentManager: FragmentManager?,
            private val screenTabContainer: ScreenTabContainer<SupportAppScreen>,
            activity: AppCompatActivity,
            container: Int
    ) : SupportAppNavigator(activity, fragmentManager, container) {

        init {
            this.fragmentManager?.beginTransaction()?.apply {
                screenTabContainer.getScreens().forEach {
                    add(container, it.fragment)
                    hide(it.fragment)
                }
                commitNow()
            }
        }

        override fun fragmentReplace(command: Replace) {
            this.fragmentManager?.beginTransaction()?.apply {
                screenTabContainer.getScreens().forEach {
                    if (it == command.screen) {
                        show(it.fragment)
                    }
                    else {
                        hide(it.fragment)
                    }
                }
                commitNow()
            }
        }

    }

    companion object {
        fun getInstance(navigationQualifier: String): SearchNavigationFragment {
            val fragment = SearchNavigationFragment()

            val arguments = Bundle()
            arguments.putString(NAVIGATION_QUALIFIERS, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }
    }
}