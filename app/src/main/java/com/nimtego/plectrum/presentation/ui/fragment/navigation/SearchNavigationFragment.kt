package com.nimtego.plectrum.presentation.ui.fragment.navigation

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.SearchNavigationPresenter
import com.nimtego.plectrum.presentation.navigation.*
import com.nimtego.plectrum.presentation.ui.auxiliary.ParentRouterProvider
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseSearchNavFragment
import com.nimtego.plectrum.presentation.utils.TabSelectedListener
import kotlinx.android.synthetic.main.navigation_search_fragment.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject
import javax.inject.Named

class SearchNavigationFragment : BaseSearchNavFragment(), ParentRouterProvider {

    @field:[Inject Named(NavigationQualifiers.BOTTOM_NAVIGATION_ROUTER_HANDLER)]
    internal lateinit var parentNavigationHandler: NavigationHandler

    @field:[Inject Named(NavigationQualifiers.SEARCH_NAVIGATION_ROUTER_HANDLER)]
    internal lateinit var searchNavigationHandler: NavigationHandler

    @Inject
    internal lateinit var searchTabScreenFabric: SearchTabScreenFabric

    @field:[Inject Named(NavigationQualifiers.SEARCH_NAVIGATION)]
    @InjectPresenter
    override lateinit var presenter: SearchNavigationPresenter

    private lateinit var screensContainer: ScreenTabContainer<SupportAppScreen>
    private lateinit var searchTabLayout: TabLayout

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.searchTabLayout = search_navigation_tab_layout
        this.screensContainer = this.searchTabScreenFabric.getScreensContainer(this.navigationQualifier)
        prepareSearchTabLayout()
    }

    private fun prepareSearchTabLayout() {
        val tabs = this.screensContainer.getTabs()
        tabs.forEach {
            this.searchTabLayout.addTab(
                    this.searchTabLayout.newTab().setText(it.getTabName()),
                    it.getTabNumber()
            )
        }
        this.searchTabLayout.apply {
            addOnTabSelectedListener(object : TabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.let {
                        this@SearchNavigationFragment.presenter.tabSelected(tab.text.toString())
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    this@SearchNavigationFragment.presenter.tabSelected(tab.text.toString())
                }
            })
        }
    }

    override fun selectTab(number: Int) {
        this.searchTabLayout.getTabAt(number)?.select()
    }

    override fun provideNavigator(): Navigator? {
        return context?.let {
            SearchNavigator(childFragmentManager,
                    this.searchTabScreenFabric.getScreensContainer(this.navigationQualifier),
                    it as AppCompatActivity,
                    this.layoutContainer,
                    this.parentNavigationHandler.getRouter(this.navigationQualifier))
        }
    }

    override fun getParentRouter(): Router {
        return this.searchNavigationHandler.getRouter(navigationQualifier)
    }

// MARK: - Inner Types

    private inner class SearchNavigator(
            private val fragmentManager: FragmentManager?,
            screenTabContainer: ScreenTabContainer<SupportAppScreen>,
            activity: AppCompatActivity,
            container: Int,
            parentRouter: Router
    ) : ParentHolderFragmentNavigator(activity, fragmentManager, container, parentRouter) {

        private val fragmentMap = screenTabContainer.getScreens().map {
            (it as Screens.SearchNavTabScreen).navigationQualifier to it.fragment
        }.toMap()

        init {
            this.fragmentManager?.beginTransaction()?.apply {
                fragmentMap.mapValues {
                    add(container, it.value)
                    hide(it.value)
                }
                commitNow()
            }
        }

        override fun fragmentReplace(command: Replace) {
            this.fragmentManager?.beginTransaction()?.apply {
                fragmentMap.forEach { (key, fragment) ->
                    if (key == (command.screen as Screens.SearchNavTabScreen).navigationQualifier) {
                        println("$key        ${command.screen}")
                        show(fragment)
                    } else {
                        println("$key else")
                        hide(fragment)
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