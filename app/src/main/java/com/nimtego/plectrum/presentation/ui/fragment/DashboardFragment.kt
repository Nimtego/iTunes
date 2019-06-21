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
import com.nimtego.plectrum.presentation.mvp.view.DashBoardView
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.utils.toast.SimpleToastAlarm
import com.nimtego.plectrum.presentation.utils.toast.ToastAlarm
import javax.inject.Inject




class DashboardFragment : BaseFragment(), DashBoardView {

    override fun message(message: String?) {

    }

    lateinit var  toast: ToastAlarm
    private var dashBoardContainer: FrameLayout? = null

    @Inject
    @InjectPresenter
    internal lateinit var presenter: DashBoardPresenter

    @ProvidePresenter
    fun provideRepositoryPresenter(): DashBoardPresenter {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dashboard_fragment_k, container, false)
        toast = SimpleToastAlarm(this.context)
        initRV(view)
        return view
    }

    protected fun initRV(view: View) {
        this.dashBoardContainer = view.findViewById(R.id.dash_board_container)

        //todo navigation
        val tabFragment = Screens.MusicTabView().fragment
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.dash_board_container, tabFragment)
        transaction.commit()
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun getInstance() = DashboardFragment()
    }
}