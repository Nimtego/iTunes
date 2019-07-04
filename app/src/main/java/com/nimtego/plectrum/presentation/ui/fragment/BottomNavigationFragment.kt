package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.presenters.BottomNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.RouterProvider
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import com.nimtego.plectrum.presentation.navigation.LocalCiceroneHolder
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject


class BottomNavigationFragment : BaseFragment(), MainBottomNavigationView, BackButtonListener {

    override val layoutRes: Int =  R.layout.bottom_navigation_fragment

    private lateinit var currentTab: SupportAppScreen
    private var bottomNavigationView: AHBottomNavigation? = null

    private val currentTabFragment: BaseFragment?
        get() = childFragmentManager.fragments.firstOrNull { !it.isHidden } as? BaseFragment

//    @Inject
//    internal lateinit var router: Router

//    private var navigator: Navigator? = null

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    @Inject
    @InjectPresenter
    internal lateinit var presenter: BottomNavigationPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): BottomNavigationPresenter {
        return presenter
    }

//    override fun onResume() {
//        super.onResume()
//        getCicerone().navigatorHolder.setNavigator(getNavigator())
////    }
//
//    override fun onPause() {
//        getCicerone().navigatorHolder.removeNavigator()
//        super.onPause()
//    }
//    private fun getCicerone(): Cicerone<Router> {
//        return ciceroneHolder.getCicerone(getContainerName())
//    }



//    private fun getNavigator(): Navigator {
//        if (navigator == null) {
//            navigator = SupportAppNavigator(activity, childFragmentManager, R.id.bottom_navigation_container)
//        }
//        return navigator as Navigator
//    }

//    override fun getRouter(): Router {
//        return router
//    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.bottom_navigation_container)
        if (fragment != null
                && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed()) {
            return true
        } else {
            (activity as RouterProvider).getRouter().exit()
            return true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        presenter.router = this.router
        this.bottomNavigationView = this.view?.findViewById(R.id.bottomNavigationView)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {

        AHBottomNavigationAdapter(activity, R.menu.navigation).apply {
            setupWithBottomNavigation(bottomNavigationView)
        }
        with(bottomNavigationView) {
            this?.accentColor = context.getColor(R.color.color_navigation_item_active)
            this?.inactiveColor = context.getColor(R.color.color_navigation_item_inactive)

            this?.setOnTabSelectedListener { position, wasSelected ->
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
            this?.setNotificationMarginLeft(leftMargin, leftMargin)
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
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//Mark: private

    private fun selectTab(tab: SupportAppScreen) {

        val currentFragment = currentTabFragment
        val newFragment = childFragmentManager.findFragmentByTag(tab.screenKey)

        if (currentFragment != null && newFragment != null && currentFragment == newFragment) return

        childFragmentManager.beginTransaction().apply {
            if (newFragment == null) add(R.id.bottom_navigation_container, createTabFragment(tab), tab.screenKey)

            currentFragment?.let {
                hide(it)
                it.userVisibleHint = false
            }
            newFragment?.let {
                show(it)
                it.userVisibleHint = true
            }
        }.commitNow()
    }

    private fun createTabFragment(tab: SupportAppScreen) = tab.fragment



//    private fun getContainerName(): String {
//        return currentTab?.screenKey
//    }

    companion object {
        fun getInstance() : BottomNavigationFragment {
            val fragment = BottomNavigationFragment()

            val arguments = Bundle()
            arguments.putString(MAIN_TAB_FRAGMENT, MAIN_TAB_FRAGMENT)
            fragment.arguments = arguments

            return fragment
        }
        const val MAIN_TAB_FRAGMENT = "main_tab_fragment"
        val MUSIC_TAB = Screens.MusicTabContentView
        val MOVIE_TAB = Screens.MovieTabContentView
        val BOOK_TAB = Screens.BookTabContentView
    }
}