package com.nimtego.plectrum.presentation.migrate_kotlin.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.WindowManager
import android.widget.FrameLayout
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.mvp.MainView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : MvpAppCompatActivity(), MainView {

//    @Inject
//    var navigatorHolder: NavigatorHolder? = null

    private var conteiner: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //todo add module
        //App.INSTANCE.getAppComponent().inject(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_k)
        //set transparent status bar
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        this.conteiner = this.findViewById(R.id.main_container)
        supportFragmentManager
                .beginTransaction()
                .add(
                        R.id.main_container,
                        DashboardFragment.getInstance()
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
