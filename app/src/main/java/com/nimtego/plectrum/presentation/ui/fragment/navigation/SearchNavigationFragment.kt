package com.nimtego.plectrum.presentation.ui.fragment.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.TabNavigationPresenter
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseInnerNavFragment
import com.nimtego.plectrum.presentation.ui.fragment.search.SearchContentFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject
import javax.inject.Named

class SearchNavigationFragment : BaseInnerNavFragment() {

    @field:[Inject Named(NavigationQualifiers.SEARCH_MUSIC_NAVIGATION)]
    override lateinit var navigatorHolder: NavigatorHolder

    @field:[Inject Named(NavigationQualifiers.SEARCH_MUSIC_NAVIGATION)]
    @InjectPresenter
    override lateinit var presenter: TabNavigationPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): TabNavigationPresenter {
        this.presenter.setNavigationQualifiers(NavigationQualifiers.SEARCH_MUSIC_NAVIGATION)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.navigator?.applyCommands(
                arrayOf(
                        Replace(Screens.SearchContentScreen(
                                NavigationQualifiers.SEARCH_MUSIC_NAVIGATION))))
    }

    override fun showSearchTabs(showTabs: Boolean) {
        //todo !!!!!!
//        if (showTabs) {
//            (parentFragment as MainBottomNavigationView).withInnerTopNavigation(
//                    listOf("Track", "Album", "Author", "Test 1", "Test 2", "Test 3")
//            )
//        }
//        else {
//            (parentFragment as MainBottomNavigationView).closeInnerTopNavigation()
//        }
    }

    override fun provideNavigator(): Navigator? {
        return context?.let {
            MusicTabNavigator(childFragmentManager,
                    it as AppCompatActivity,
                    this.layoutContainer)
        }
    }

// MARK: - Inner Types

    private inner class MusicTabNavigator(
            fragmentManager: FragmentManager?,
            activity: AppCompatActivity,
            container: Int
    ) : SupportAppNavigator(activity, fragmentManager, container) {

        override fun createFragment(screen: SupportAppScreen): Fragment? {
            return when (screen) {
//                Screens.MusicTabScreen -> screen.fragment
//                is Screens.MoreContentScreen -> screen.fragment
//                is Screens.ItemInformationScreen -> screen.fragment
                is Screens.SearchContentScreen -> screen.fragment
                else -> null
            }
        }

        override fun fragmentForward(command: Forward?) {
            if (command?.screen is Screens.SearchContentScreen) {
                val fm = childFragmentManager
                val fragment: Fragment?
                val fragments = fm.fragments
                fragment = fragments?.firstOrNull { it.isVisible }
                if (fragment != null
                        && fragment is SearchContentFragment) {
                    fragmentReplace(Replace(command.screen))
                } else {
                    super.fragmentForward(command)
                }
            } else {
                super.fragmentForward(command)
            }
        }
    }

    companion object {
        fun getInstance(navigationQualifier: String): SearchNavigationFragment {
            val fragment = SearchNavigationFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TabName"
    }
}