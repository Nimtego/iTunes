package com.nimtego.plectrum.presentation.migrate_kotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter

import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.migrate_kotlin.mvp.DashboardView
import com.nimtego.plectrum.presentation.migrate_kotlin.presenters.DashboardPresenter
import com.nimtego.plectrum.presentation.migrate_kotlin.view_model.DashboardViewModel

class DashboardFragment : MvpAppCompatFragment(), DashboardView {

    @InjectPresenter
    internal lateinit var presenter: DashboardPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }


    override fun showViewState(dashboardViewModel: DashboardViewModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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