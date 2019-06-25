package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.presenters.BottomNavigationPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.RouterProvider
import com.nimtego.plectrum.presentation.mvp.view.MainBottomNavigationView
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import com.nimtego.plectrum.presentation.utils.toast.SimpleToastAlarm
import com.nimtego.plectrum.presentation.utils.toast.ToastAlarm
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import com.nimtego.plectrum.presentation.navigation.LocalCiceroneHolder
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppNavigator



class BottomNavigationFragment : BaseFragment(), MainBottomNavigationView, RouterProvider, BackButtonListener {

    lateinit var  toast: ToastAlarm
    private var navigator: Navigator? = null
    private var bottomNavigationView: BottomNavigationView? = null

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    @Inject
    @InjectPresenter
    internal lateinit var presenter: BottomNavigationPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): BottomNavigationPresenter {
        return presenter
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

    override fun message(message: String?) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_navigation_fragment, container, false)
        toast = SimpleToastAlarm(this.context)
        this.bottomNavigationView = view.findViewById(R.id.bottomNavigationView)
        initViews()

        if (savedInstanceState == null) {
            bottomNavigationView?.setSelectedItemId(R.id.navigation_music)
        }

        return view
    }

    private fun initViews() {
        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
//            bottomNavigationView?.itemIconTintList. = null
            when (item.itemId) {
                R.id.navigation_music -> {
                    message(item.itemId.toString())
                    selectTab(MUSIC_TAB)
                }
                R.id.navigation_movie -> {
                    message(item.itemId.toString())
                    selectTab(MOVIE_TAB)
                }
                R.id.navigation_books -> {
                    message(item.itemId.toString())
                    selectTab(BOOK_TAB)
                }
            }
            false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.bottom_navigation_container) == null) {
            getCicerone().router.replaceScreen(Screens.TabContentView(getContainerName()))
        }
    }
    override fun onResume() {
        super.onResume()
        getCicerone().navigatorHolder.setNavigator(getNavigator())
    }

    override fun onPause() {
        getCicerone().navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun getNavigator(): Navigator {
        if (navigator == null) {
            navigator = SupportAppNavigator(activity, childFragmentManager, R.id.bottom_navigation_container)
        }
        return navigator as Navigator
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//Mark: private

    private fun selectTab(tab: String) {
        val fm = childFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.getFragments()
        if (fragments != null) {
            for (f in fragments!!) {
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
            transaction.add(R.id.bottom_navigation_container, Screens.TabScreen(tab).getFragment(), tab)
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }


    private fun getCicerone(): Cicerone<Router> {
        return ciceroneHolder.getCicerone(getContainerName())
    }

    private fun getContainerName(): String {
        return arguments.getString(MAIN_TAB_FRAGMENT)
    }

    companion object {
        fun getInstance() : BottomNavigationFragment {
            val fragment = BottomNavigationFragment()

            val arguments = Bundle()
            arguments.putString(MAIN_TAB_FRAGMENT, MAIN_TAB_FRAGMENT)
            fragment.setArguments(arguments)

            return fragment
        }
        const val MAIN_TAB_FRAGMENT = "main_tab_fragment"
        const val MUSIC_TAB = "music_tab"
        const val MOVIE_TAB = "movie_tab"
        const val BOOK_TAB = "book_tab"
    }
}