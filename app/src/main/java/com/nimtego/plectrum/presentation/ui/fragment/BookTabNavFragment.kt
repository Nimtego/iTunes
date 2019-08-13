package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.TabNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import com.nimtego.plectrum.presentation.navigation.ParentHolderFragmentNavigator
import com.nimtego.plectrum.presentation.navigation.Screens
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject
import javax.inject.Named

class BookTabNavFragment : BaseNavFragment() {

    @field:[Inject Named(NavigationQualifiers.TAB_BOOK_NAVIGATION)]
    override lateinit var navigatorHolder: NavigatorHolder

    @field:[Inject Named(NavigationQualifiers.TAB_BOOK_NAVIGATION)]
    @InjectPresenter
    override lateinit var presenter: TabNavigationPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): TabNavigationPresenter {
        this.presenter.setNavigationQualifiers(NavigationQualifiers.TAB_BOOK_NAVIGATION)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.navigator?.applyCommands(arrayOf(Replace(Screens.BookTabScreen)))
    }

    override fun showSearchTabs(showTabs: Boolean) {
        if (showTabs) {
            (parentFragment as MainBottomNavigationView).withInnerTopNavigation(
                    listOf("Book", "Audio book", "Author", "Test 1", "Test 2", "Test 3" )
            )
        }
        else {
            (parentFragment as MainBottomNavigationView).closeInnerTopNavigation()
        }
    }


    override fun provideNavigator(): Navigator? {
        return context?.let {
            BookTabNavigator(childFragmentManager,
                    it as AppCompatActivity,
                    this.layoutContainer,
                    this.bottomBarRouter)
        }
    }

// MARK: - Inner Types

    private inner class BookTabNavigator(
            fragmentManager: FragmentManager?,
            activity: AppCompatActivity,
            container: Int,
            parentRouter: Router
    ) : ParentHolderFragmentNavigator(activity, fragmentManager, container, parentRouter) {

        override fun createFragment(screen: SupportAppScreen): Fragment? {
            return when (screen) {
                Screens.BookTabScreen -> screen.fragment
                is Screens.MoreContentScreen -> screen.fragment
                is Screens.ItemInformationScreen -> screen.fragment
                is Screens.SearchScreen -> screen.fragment
                else -> null
            }
        }

        override fun fragmentForward(command: Forward?) {
            if (command?.screen is Screens.SearchScreen) {
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
        fun getInstance(): BookTabNavFragment {
            val fragment = BookTabNavFragment()
            val arguments = Bundle()

            arguments.putString(TAB_NAME, "Book_nav_fragment")
            fragment.arguments = arguments

            return fragment
        }

        const val TAB_NAME = "TAB_NAME"
    }
}