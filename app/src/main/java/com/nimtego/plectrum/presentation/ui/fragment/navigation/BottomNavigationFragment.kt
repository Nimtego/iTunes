package com.nimtego.plectrum.presentation.ui.fragment.navigation

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
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
import com.nimtego.plectrum.presentation.manger.TabsProvider
import com.nimtego.plectrum.presentation.mvp.presenters.navigation.BottomNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.navigation.Tab
import com.nimtego.plectrum.presentation.ui.auxiliary.TabContainer
import com.nimtego.plectrum.presentation.ui.fragment.base.BaseFragment
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import com.nimtego.plectrum.presentation.utils.HideChangeListener
import com.nimtego.plectrum.presentation.utils.TabSelectedListener
import kotlinx.android.synthetic.main.bottom_navigation_fragment.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Replace
import rx.Subscriber
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

    @Inject
    internal lateinit var tabsProvider: TabsProvider

    private lateinit var bottomNavigationView: AHBottomNavigation
    private lateinit var topNavigationView: TabLayout
    private lateinit var searchText: SearchView
    private lateinit var searchTabListener: (Tab) -> Unit
    private var tabContainer: TabContainer? = null
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
            //todo remove from hear
            //closeInnerTopNavigation()
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
        this.topNavigationView = top_navigation_view
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

    override fun initSearchTabNavigation(tabContainer: TabContainer?) {
        tabContainer?.let {
            this.tabContainer = it
            this.searchTabListener = it.listener()
            updateTopNavigation()
        } ?: run {
            this.tabContainer = null
            closeInnerTopNavigation()
        }
    }

    private fun updateTopNavigation() {
        this.topNavigationView.removeAllTabs()
        this.tabContainer?.listTabs()?.forEach {
            this.topNavigationView.addTab(
                    this.topNavigationView.newTab().setText(it.getTabName()),
                    it.getTabNumber()
            )
        }
        this.topNavigationView.apply {
            addOnTabSelectedListener(object : TabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.let {
                        this@BottomNavigationFragment.tabContainer?.get(topNavigationView.selectedTabPosition)?.let {
                            this@BottomNavigationFragment.searchTabListener(it)
                        }
                    }
                }
            })
            visibility = TabLayout.VISIBLE
            expandSearchLayer()
            this@BottomNavigationFragment.tabContainer?.getCurrentTab()?.let {
                getTabAt(it.getTabNumber())?.select()

            }
        }
    }

    private fun closeInnerTopNavigation() {
        this.topNavigationView.removeAllTabs()
        this.topNavigationView.visibility = TabLayout.GONE
        expandSearchLayer()
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
                            ?.hideWithCall(this.movieNavigationFragment)
                            ?.hideWithCall(this.bookNavigationFragment)
                            ?.showWithCall(this.musicNavigationFragment)
                            ?.commit()
                }
                Screens.MovieTabNavigationScreen -> {
                    this.fragmentManager?.beginTransaction()
                            ?.hideWithCall(this.musicNavigationFragment)
                            ?.hideWithCall(this.bookNavigationFragment)
                            ?.showWithCall(this.movieNavigationFragment)
                            ?.commit()
                }
                Screens.BookTabNavigationScreen -> {
                    this.fragmentManager?.beginTransaction()
                            ?.hideWithCall(this.musicNavigationFragment)
                            ?.hideWithCall(this.movieNavigationFragment)
                            ?.showWithCall(this.bookNavigationFragment)
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

private fun FragmentTransaction.showWithCall(fragment: Fragment): FragmentTransaction {
    val fragmentTransaction = this.show(fragment)
    (fragment as HideChangeListener).isShow(true)
    return fragmentTransaction
}

private fun FragmentTransaction.hideWithCall(fragment: Fragment): FragmentTransaction {
    (fragment as HideChangeListener).isShow(false)
    return this.hide(fragment)
}
