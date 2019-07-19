package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.nimtego.plectrum.presentation.mvp.view.BaseView
import com.nimtego.plectrum.presentation.ui.activity.AppActivity
import com.nimtego.plectrum.presentation.ui.widget.toast.SimpleToastAlarm
import com.nimtego.plectrum.presentation.ui.widget.toast.ToastAlarm
import com.nimtego.plectrum.presentation.utils.StatusBarUtils

abstract class BaseFragment :  MvpAppCompatFragment(), BaseView {

    abstract val layoutRes: Int
    lateinit var systemMessage: ToastAlarm

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(layoutRes, container, false)

    open fun setupStatusBar() {
        StatusBarUtils.makeIconsDark(this.context, this.activity?.window)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.systemMessage = SimpleToastAlarm(this.context)
        setupStatusBar()
    }

    override fun systemMessage(message: String) {
       this.systemMessage.message(message)
    }

    open fun showStatusBar(): Boolean = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (this.activity as AppActivity).showStatusBar(showStatusBar())
    }

    override fun onDestroy() {
        if (this.activity?.isFinishing == true) {
            (this.activity as AppActivity).showStatusBar(true)
        }
        super.onDestroy()
    }
}