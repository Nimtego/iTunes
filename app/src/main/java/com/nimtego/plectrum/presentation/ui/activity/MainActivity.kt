package com.nimtego.plectrum.presentation.ui.activity

import android.os.Bundle
import android.view.WindowManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.view.MainView
import com.nimtego.plectrum.presentation.ui.fragment.BottomNavigationFragment
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainActivity : MvpAppCompatActivity(), MainView {


    private lateinit var navigator: SupportAppNavigator

    override fun onResume() {
        super.onResume()
        App.INSTANCE.getNavigatorHolder().setNavigator(navigator)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        //todo add module
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        //set transparent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        navigator = SupportAppNavigator(this, R.id.main_container)


        supportFragmentManager
                .beginTransaction()
                .add(
                        R.id.main_container,
                        BottomNavigationFragment.getInstance()
                )
                .commit()
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

//Mark: private

}
