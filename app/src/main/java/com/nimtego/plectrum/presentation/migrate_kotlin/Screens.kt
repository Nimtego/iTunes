package com.nimtego.plectrum.presentation.migrate_kotlin

import android.content.Context
import ru.terrakok.cicerone.android.support.SupportAppScreen
import android.content.Intent
import android.support.v4.app.Fragment
import com.nimtego.plectrum.presentation.migrate_kotlin.ui.DashboardFragment
import com.nimtego.plectrum.presentation.migrate_kotlin.ui.MainActivity


class Screens {
    class SampleScreen(private val number: Int) : SupportAppScreen() {

        init {
            this.screenKey = javaClass.simpleName + "_" + number
        }

        override fun getFragment(): Fragment {
            return DashboardFragment.getInstance()
        }
    }

    class StartScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    class MainScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

//    class BottomNavigationScreen : SupportAppScreen() {
//        override fun getActivityIntent(context: Context): Intent {
//            return Intent(context, BottomNavigationActivity::class.java)
//        }
//    }

//    class TabScreen(private val tabName: String) : SupportAppScreen() {
//
//        override fun getFragment(): Fragment {
//            return TabContainerFragment.getNewInstance(tabName)
//        }
//    }

    class ForwardScreen(private val containerName: String, private val number: Int) : SupportAppScreen() {

//        override fun getFragment(): Fragment {
//            return ForwardFragment.getNewInstance(containerName, number)
//        }
//    }
//
//    class GithubScreen : SupportAppScreen() {
//        fun getActivityIntent(context: Context): Intent {
//            return Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/terrakok/Cicerone"))
//        }
//    }
//
//    class ProfileScreen : SupportAppScreen() {
//        fun getActivityIntent(context: Context): Intent {
//            return Intent(context, ProfileActivity::class.java)
//        }
//    }
//
//    class ProfileInfoScreen : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return ProfileFragment()
//        }
//    }
//
//    class SelectPhotoScreen : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return SelectPhotoFragment()
//        }
    }
}