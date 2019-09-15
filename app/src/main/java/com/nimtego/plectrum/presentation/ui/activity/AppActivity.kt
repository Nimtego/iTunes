package com.nimtego.plectrum.presentation.ui.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nimtego.plectrum.App
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.navigation.Screens
import com.nimtego.plectrum.presentation.ui.massage.SystemMessageNotifier
import com.nimtego.plectrum.presentation.ui.massage.SystemMessageType
import com.nimtego.plectrum.presentation.utils.BackButtonListener
import io.reactivex.disposables.Disposable
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject
import javax.inject.Named

class AppActivity : MvpAppCompatActivity() {

    private fun tag(): String = this::class.java.simpleName

    private lateinit var statusBar: FrameLayout

    @Inject
    lateinit var systemMessageNotifier: SystemMessageNotifier

    private val navigator: Navigator = AppNavigator(this.supportFragmentManager, R.id.main_container)

    @field:[Inject Named(NavigationQualifiers.APP_NAVIGATION)]
    internal lateinit var appNavigationHolder: NavigatorHolder

    @field:[Inject Named(NavigationQualifiers.APP_NAVIGATION)]
    internal lateinit var appRouter: Router

    private var notifierDisposable: Disposable? = null

//Activity override

    override fun onCreate(savedInstanceState: Bundle?) {
        App.INSTANCE.getAppComponent().inject(this)
        //setTheme(R.style.MyMaterialTheme)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        this.statusBar = findViewById(R.id.status_bar)

        onLockOrientation()
        //setTransparentStatusBar()

        this.navigator.applyCommands(arrayOf(Forward(Screens.SplashScreen)))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        subscribeOnSystemMessages()
        this.appNavigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        this.appNavigationHolder.removeNavigator()
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

    // @link - https://stackoverflow.com/questions/45527229/showing-content-behind-status-and-navigation-bar/45530210
    fun setTransparentStatusBar() {
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    private fun showAlertMessage(message: String) {
        showToastMessage(message) //tmp
        //todo dialog fragment
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

    private inner class AppNavigator(fragmentManager: FragmentManager, containerId: Int) :
           SupportAppNavigator(this@AppActivity, fragmentManager, containerId) {

        override fun createFragment(screen: SupportAppScreen?): Fragment {
            return when (screen) {
                Screens.SplashScreen -> screen.fragment
                Screens.BottomNavigationScreen -> screen.fragment
                else -> throw RuntimeException("Unknown screen key!")
            }
        }
    }
}
