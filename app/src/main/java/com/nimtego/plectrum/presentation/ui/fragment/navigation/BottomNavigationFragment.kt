package com.nimtego.plectrum.presentation.ui.fragment.navigation

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.BottomNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import com.nimtego.plectrum.presentation.utils.HideChangeListener
import kotlinx.android.synthetic.main.bottom_navigation_fragment.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject
import javax.inject.Named


class BottomNavigationFragment : BaseFragment(), MainBottomNavigationView, BackButtonListener {

    override val layoutRes: Int = R.layout.bottom_navigation_fragment

    @field:[Inject Named(NavigationQualifiers.BOTTOM_BAR_NAVIGATION)]
    internal lateinit var bottomNavigatorHolder: NavigatorHolder

    private var navigator: Navigator? = null

    @Inject
    @InjectPresenter
    internal lateinit var presenter: BottomNavigationPresenter

    private lateinit var bottomNavigationView: AHBottomNavigation
    private lateinit var searchText: SearchView
    private lateinit var appBar: AppBarLayout

    private val currentTabFragment: BaseFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? BaseFragment


    @ProvidePresenter
    fun provideRepositoryPresenter(): BottomNavigationPresenter {
        return presenter
    }

    override fun onBackPressed(): Boolean {
        val fm = childFragmentManager
        val fragment: Fragment?
        val fragments = fm.fragments
        fragment = fragments?.firstOrNull { it.isVisible }
        return if (fragment != null
                && fragment is BackButtonListener) {
            fragment.onBackPressed()
        } else {
            presenter.onBackPressed()
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
                this.navigator = BottomNavigator(childFragmentManager,
                        it as AppCompatActivity,
                        R.id.bottom_navigation_container)
            }
        }
        this.bottomNavigationView = bottom_navigation_view
        this.searchText = search_edit_text
        this.appBar = app_bar
        initSearchView()
        initBottomNavigation()
    }

    private fun initSearchView() {
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {
                searchText.onActionViewCollapsed()
                presenter.searchTextSubmit(text)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        this.bottomNavigatorHolder.setNavigator(this.navigator)
    }

    override fun onPause() {
        this.bottomNavigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun expandSearchLayer() {
        this.appBar.setExpanded(true)
    }

    private fun initBottomNavigation() {

        AHBottomNavigationAdapter(activity, R.menu.navigation).apply {
            setupWithBottomNavigation(bottomNavigationView)

        }
        with(bottomNavigationView) {
            this.accentColor = context.getColor(R.color.color_navigation_item_active)
            this.inactiveColor = context.getColor(R.color.color_navigation_item_inactive)

            this.setOnTabSelectedListener { position, wasSelected ->
                if (!wasSelected) selectTab(
                        when (position) {
                            0 -> MUSIC_TAB
                            1 -> MOVIE_TAB
                            else -> BOOK_TAB
                        }
                )
                true
            }
            val leftMargin = resources.getDimension(R.dimen.padding_medium).toInt()
            this.setNotificationMarginLeft(leftMargin, leftMargin)
        }

        selectTab(
                when (currentTabFragment?.tag) {
                    MUSIC_TAB.screenKey -> MUSIC_TAB
                    MOVIE_TAB.screenKey -> MOVIE_TAB
                    BOOK_TAB.screenKey -> BOOK_TAB
                    //todo remove
                    else -> MUSIC_TAB
                }
        )

        this.bottomNavigationView.isBehaviorTranslationEnabled = false
    }

    override fun showProgress(show: Boolean) {

    }


//Mark: private

    private fun selectTab(tab: SupportAppScreen) {
        this.presenter.replaceFragment(tab)
        expandSearchLayer()
    }

// MARK: - Inner Types

    private inner class BottomNavigator(
            private val fragmentManager: FragmentManager?,
            activity: AppCompatActivity,
            container: Int
    ) : SupportAppNavigator(activity, fragmentManager, container) {

        private val musicNavigationFragment: Fragment = Screens.MusicTabNavigationScreen.fragment
        private val movieNavigationFragment: Fragment = Screens.MovieTabNavigationScreen.fragment
        private val bookNavigationFragment: Fragment = Screens.BookTabNavigationScreen.fragment

        init {
            this.fragmentManager
                    ?.beginTransaction()
                    ?.add(container, this.musicNavigationFragment)
                    ?.hide(this.musicNavigationFragment)
                    ?.add(container, this.movieNavigationFragment)
                    ?.hide(this.movieNavigationFragment)
                    ?.add(container, this.bookNavigationFragment)
                    ?.hide(this.bookNavigationFragment)
                    ?.commitNow()
        }

        override fun fragmentReplace(command: Replace) {
            when (command.screen) {
                Screens.MusicTabNavigationScreen -> {
                    this.fragmentManager?.beginTransaction()
                            ?.hide(this.movieNavigationFragment)
                            ?.hide(this.bookNavigationFragment)
                            ?.show(this.musicNavigationFragment)
                            ?.commit()
                }
                Screens.MovieTabNavigationScreen -> {
                    this.fragmentManager?.beginTransaction()
                            ?.hide(this.musicNavigationFragment)
                            ?.hide(this.bookNavigationFragment)
                            ?.show(this.movieNavigationFragment)
                            ?.commit()
                }
                Screens.BookTabNavigationScreen -> {
                    this.fragmentManager?.beginTransaction()
                            ?.hide(this.musicNavigationFragment)
                            ?.hide(this.movieNavigationFragment)
                            ?.show(this.bookNavigationFragment)
                            ?.commit()

                }
            }
        }

    }

    companion object {
        fun getInstance(): BottomNavigationFragment {
            val fragment = BottomNavigationFragment()
            val arguments = Bundle()

            arguments.putString(MAIN_TAB_FRAGMENT, MAIN_TAB_FRAGMENT)
            fragment.arguments = arguments

            return fragment
        }

        const val MAIN_TAB_FRAGMENT = "main_tab_fragment"
        val MUSIC_TAB = Screens.MusicTabNavigationScreen
        val MOVIE_TAB = Screens.MovieTabNavigationScreen
        val BOOK_TAB = Screens.BookTabNavigationScreen
    }
}

