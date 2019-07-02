package com.nimtego.plectrum.presentation.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.ui.fragment.BottomNavigationFragment
import com.nimtego.plectrum.presentation.ui.massage.SystemMessageNotifier
import com.nimtego.plectrum.presentation.ui.massage.SystemMessageType
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class AppActivity : MvpAppCompatActivity() {

    private fun tag(): String = this::class.java.simpleName

    private lateinit var statusBar: FrameLayout

    @Inject
    lateinit var systemMessageNotifier: SystemMessageNotifier

    @Inject
    lateinit var routet: Router

    private lateinit var navigator: Navigator

    private var notifierDisposable: Disposable? = null

//Activity override

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        setTheme(R.style.MyMaterialTheme)
        //todo launch
//        appLauncher.onLaunch()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        this.statusBar = findViewById(R.id.status_bar)
        this.navigator = SupportAppNavigator(this, R.id.main_container)

        onLockOrientation()
        setTransparentStatusBar()

        supportFragmentManager.beginTransaction().add(
                        R.id.main_container,
                        BottomNavigationFragment.getInstance()
        ).commit()

        if (savedInstanceState == null) {
//            appLauncher.coldStart()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        subscribeOnSystemMessages()
        //navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        //navigatorHolder.removeNavigator()
        unsubscribeOnSystemMessages()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.main_container)
        var result = false

        if (fragment is BackButtonListener) {
            result = fragment.onBackPressed()
        }

        if (!result) {
            super.onBackPressed()
        }
    }

    fun showStatusBar(show: Boolean = true) {
        this.statusBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun setTransparentStatusBar() {
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun showAlertMessage(message: String) {
        showToastMessage(message) //tmp
        //todo dialog fragment
//        MessageDialogFragment.create(
//                message = message
//        ).show(supportFragmentManager, null)
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun subscribeOnSystemMessages() {
        notifierDisposable = systemMessageNotifier.notifier
                .subscribe { msg ->
                    when (msg.type) {
                        SystemMessageType.ALERT -> showAlertMessage(msg.text)
                        SystemMessageType.TOAST -> showToastMessage(msg.text)
                    }
                }
    }

    private fun unsubscribeOnSystemMessages() {
        notifierDisposable?.dispose()
    }

// MARK: - Private methods

    private fun onLockOrientation(portrait: Boolean = true) {
        this.requestedOrientation = if (portrait) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                                    else  ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}
