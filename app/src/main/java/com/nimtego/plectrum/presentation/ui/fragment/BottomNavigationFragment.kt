package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
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
import javax.inject.Inject


class BottomNavigationFragment : BaseFragment(), MainBottomNavigationView, RouterProvider, BackButtonListener {

    override val layoutRes: Int =  R.layout.bottom_navigation_fragment

    private lateinit var currentTab: String
    private var bottomNavigationView: BottomNavigationView? = null

    @Inject
    internal lateinit var router: Router

    private var navigator: Navigator? = null

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    @Inject
    @InjectPresenter
    internal lateinit var presenter: BottomNavigationPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): BottomNavigationPresenter {
        return presenter
    }

    override fun onResume() {
        super.onResume()
        getCicerone().navigatorHolder.setNavigator(getNavigator())
    }

    override fun onPause() {
        getCicerone().navigatorHolder.removeNavigator()
        super.onPause()
    }
    private fun getCicerone(): Cicerone<Router> {
        return ciceroneHolder.getCicerone(getContainerName())
    }



    private fun getNavigator(): Navigator {
        if (navigator == null) {
            navigator = SupportAppNavigator(activity, childFragmentManager, R.id.bottom_navigation_container)
        }
        return navigator as Navigator
    }

    override fun getRouter(): Router {
        return getCicerone().router
    }

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
        presenter.router = this.router
        this.bottomNavigationView = view?.findViewById(R.id.bottomNavigationView)
        initBottomNavigation()
        bottomNavigationView?.selectedItemId = R.id.navigation_movie
        router.replaceScreen(Screens.TabContentView(getContainerName()))
    }

    private fun initBottomNavigation() {
        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_music -> selectTab(MUSIC_TAB)
                R.id.navigation_movie -> selectTab(MOVIE_TAB)
                R.id.navigation_books -> selectTab(BOOK_TAB)
            }
            true
        }
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//Mark: private

    private fun selectTab(tab: String) {
        this.currentTab = tab
        val sb = StringBuilder()
        //ciceroneHolder.getCicerone(tab).router.navigateTo(Screens.TabContentView(tab))
        val fm = childFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        if (fragments != null) {
            sb.append("fragments != null")
            for (f in fragments) {
                if (f.isVisible()) {
                    currentFragment = f
                    break
                }
            }
        }
        val newFragment = fm.findFragmentByTag(tab)

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return

        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            sb.append("\nnewFragment == null")
            transaction.add(R.id.bottom_navigation_container, Screens.TabScreen(tab).fragment, tab)
        }

        if (currentFragment != null) {
            sb.append("\ncurrentFragment != null")
            transaction.hide(currentFragment)
        }

        if (newFragment != null ) {
            sb.append("\nnewFragment != null")
            transaction.show(newFragment)
        }

        systemMessage(sb.toString())
        transaction.commitNow()
    }



    private fun getContainerName(): String {
        return currentTab
    }

    companion object {
        fun getInstance() : BottomNavigationFragment {
            val fragment = BottomNavigationFragment()

            val arguments = Bundle()
            arguments.putString(MAIN_TAB_FRAGMENT, MAIN_TAB_FRAGMENT)
            fragment.arguments = arguments

            return fragment
        }
        const val MAIN_TAB_FRAGMENT = "main_tab_fragment"
        const val MUSIC_TAB = "music_tab"
        const val MOVIE_TAB = "movie_tab"
        const val BOOK_TAB = "book_tab"
    }
}