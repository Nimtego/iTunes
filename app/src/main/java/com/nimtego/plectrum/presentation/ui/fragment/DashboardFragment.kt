package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.presenters.DashBoardPresenter
import com.nimtego.plectrum.presentation.mvp.presenters.RouterProvider
import com.nimtego.plectrum.presentation.mvp.view.DashBoardView
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





class DashboardFragment : BaseFragment(), DashBoardView, RouterProvider, BackButtonListener {

    lateinit var  toast: ToastAlarm
    private var dashBoardContainer: FrameLayout? = null
    private var navigator: Navigator? = null

    @Inject
    @InjectPresenter
    internal lateinit var presenter: DashBoardPresenter

    @Inject
    lateinit var ciceroneHolder: LocalCiceroneHolder

    @ProvidePresenter
    fun provideRepositoryPresenter(): DashBoardPresenter {
        return presenter
    }

    override fun getRouter(): Router {
        return getCicerone().router
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.dash_board_container)
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
        val view = inflater.inflate(R.layout.dashboard_fragment_k, container, false)
        toast = SimpleToastAlarm(this.context)
        //initRV(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.dash_board_container) == null) {
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
            navigator = SupportAppNavigator(activity, childFragmentManager, R.id.dash_board_container)
        }
        return navigator as Navigator
    }


//    protected fun initRV(view: View) {
//        this.dashBoardContainer = view.findViewById(R.id.dash_board_container)
//
//        //todo navigation
//        val tabFragment = Screens.MusicTabView().fragment
//        val transaction = childFragmentManager.beginTransaction()
//        transaction.add(R.id.dash_board_container, tabFragment)
//        transaction.commit()
//    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//Mark: private

    private fun getCicerone(): Cicerone<Router> {
        return ciceroneHolder.getCicerone(getContainerName())
    }

    private fun getContainerName(): String {
        return arguments.getString(TAB_CONTAINER_FRAGMENT_NAME)
    }

    companion object {
        fun getInstance() : DashboardFragment {
            val fragment = DashboardFragment()

            val arguments = Bundle()
            arguments.putString(TAB_CONTAINER_FRAGMENT_NAME, TAB_CONTAINER_FRAGMENT_NAME)
            fragment.setArguments(arguments)

            return fragment
        }
        val TAB_CONTAINER_FRAGMENT_NAME = "TAB_CONTAINER_FRAGMENT_NAME"
    }
}