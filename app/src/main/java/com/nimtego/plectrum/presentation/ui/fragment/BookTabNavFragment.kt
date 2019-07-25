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
import com.nimtego.plectrum.presentation.navigation.ParentHolderFragmentNavigator
import com.nimtego.plectrum.presentation.navigation.Screens
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
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
                else -> null
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