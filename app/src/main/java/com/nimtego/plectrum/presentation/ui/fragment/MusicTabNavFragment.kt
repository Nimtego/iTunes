package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.MusicNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.TabNavigationView
import com.nimtego.plectrum.presentation.navigation.ParentHolderFragmentNavigator
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject
import javax.inject.Named

class MusicTabNavFragment : BaseFragment(), TabNavigationView, BackButtonListener {

    override val layoutRes: Int = R.layout.fragment_tab_container

    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    internal lateinit var bottomBarRouter: Router

    @field:[Inject Named(NavigationQualifiers.TAB_MUSIC_NAVIGATION)]
    internal lateinit var musicNavigatorHolder: NavigatorHolder

    private var navigator: Navigator? = null

    @Inject
    @InjectPresenter
    internal lateinit var presenter: MusicNavigationPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): MusicNavigationPresenter {
        return presenter
    }

    override fun onBackPressed(): Boolean {
        val fragment =
                this.childFragmentManager.findFragmentById(R.id.tab_layout_container)

        return if (fragment is BackButtonListener) {
            fragment.onBackPressed()
        } else {
            this.presenter.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (this.navigator == null) {
            context?.let {
                this.navigator = MusicTabNavigator(childFragmentManager,
                        it as AppCompatActivity,
                        R.id.tab_layout_container,
                        bottomBarRouter)
            }
        }
        this.navigator?.applyCommands(arrayOf(Forward(Screens.MusicTabScreen)))
    }

    override fun onResume() {
        super.onResume()
        this.musicNavigatorHolder.setNavigator(this.navigator)
    }

    override fun onPause() {
        this.musicNavigatorHolder.removeNavigator()
        super.onPause()
    }

//Mark: view override

// MARK: - Inner Types

    private inner class MusicTabNavigator(
            private val fragmentManager: FragmentManager?,
            private val activity: AppCompatActivity,
            container: Int,
            parentRouter: Router
    ) : ParentHolderFragmentNavigator(activity, fragmentManager, container, parentRouter) {

        override fun createFragment(screen: SupportAppScreen): Fragment? {
            return when (screen) {
                Screens.MusicTabScreen -> screen.fragment
                Screens.MoreContentScreen -> screen.fragment
                else -> null
            }
        }
    }

    companion object {
        fun getInstance(): MusicTabNavFragment {
            val fragment = MusicTabNavFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, "Music_nav_fragment")
            fragment.arguments = arguments

            return fragment
        }

        val TAB_NAME = "TAB_NAME"
    }
}