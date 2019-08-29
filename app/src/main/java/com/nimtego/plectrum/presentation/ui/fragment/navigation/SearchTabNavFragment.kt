package com.nimtego.plectrum.presentation.ui.fragment.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.SearchTabNavPresenter
import com.nimtego.plectrum.presentation.navigation.LocalHolder
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseTabSearchNavFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class SearchTabNavFragment : BaseTabSearchNavFragment() {

    @Inject
    lateinit var localHolder: LocalHolder

    @Inject
    @InjectPresenter
    override lateinit var presenter: SearchTabNavPresenter

    private val navigationQualifier: String
        get() = requireNotNull(this.arguments?.getString(NAVIGATION_QUALIFIERS))

    override val navigatorHolder: NavigatorHolder by lazy {
        this.localHolder.getCicerone(this.navigationQualifier).navigatorHolder as NavigatorHolder
    }

    @ProvidePresenter
    fun provideRepositoryPresenter(): SearchTabNavPresenter {
        this.presenter.setNavigationQualifiers(this.navigationQualifier)
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.navigator?.applyCommands(arrayOf(Replace(Screens.SearchContentScreen(this.navigationQualifier))))
    }

    override fun provideNavigator(): Navigator? {
        return context?.let {
            SearchTabNavigator(childFragmentManager,
                    it as AppCompatActivity,
                    this.layoutContainer)
        }
    }

// MARK: - Inner Types

    private inner class SearchTabNavigator(
            fragmentManager: FragmentManager?,
            activity: AppCompatActivity,
            container: Int
    ) : SupportAppNavigator(activity, fragmentManager, container) {

        override fun createFragment(screen: SupportAppScreen): Fragment? {
            return when (screen) {
                is Screens.SearchContentScreen -> screen.fragment
                is Screens.ItemInformationScreen -> screen.fragment
                else -> throw Exception("Screen - ${screen.screenKey} not permissible")
            }
        }

//        override fun fragmentForward(command: Forward?) {
//            if (command?.screen is Screens.SearchNavigationScreen) {
//                val fm = childFragmentManager
//                val fragment: Fragment?
//                val fragments = fm.fragments
//                fragment = fragments?.firstOrNull { it.isVisible }
//                if (fragment != null
//                        && fragment is SearchNavigationFragment) {
//                    fragmentReplace(Replace(command.screen))
//                } else {
//                    super.fragmentForward(command)
//                }
//            } else {
//                super.fragmentForward(command)
//            }
//        }
    }

    companion object {
        fun getInstance(navigationQualifier: String): SearchTabNavFragment {
            val fragment = SearchTabNavFragment()
            val arguments = Bundle()

            arguments.putString(NAVIGATION_QUALIFIERS, navigationQualifier)
            fragment.arguments = arguments

            return fragment
        }
    }
}