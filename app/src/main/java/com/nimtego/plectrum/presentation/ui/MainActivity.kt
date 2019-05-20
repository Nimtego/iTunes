package com.nimtego.plectrum.presentation.ui

import android.os.Bundle
import android.view.WindowManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.MainView


class MainActivity : MvpAppCompatActivity(), MainView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_k)
        //set transparent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

//Mark: mvp override

    override fun render(response: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
