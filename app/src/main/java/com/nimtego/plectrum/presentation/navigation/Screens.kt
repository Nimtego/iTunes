package com.nimtego.plectrum.presentation.navigation

import android.content.Context
import ru.terrakok.cicerone.android.support.SupportAppScreen
import android.content.Intent
import android.support.v4.app.Fragment
import com.nimtego.plectrum.presentation.ui.fragment.BottomNavigationFragment
import com.nimtego.plectrum.presentation.ui.activity.MainActivity
import com.nimtego.plectrum.presentation.ui.fragment.MoreSectionFragment
import com.nimtego.plectrum.presentation.ui.fragment.TabContentFragment


class Screens {
    class SampleScreen(private val number: Int) : SupportAppScreen() {

        init {
            this.screenKey = javaClass.simpleName + "_" + number
        }

        override fun getFragment(): Fragment {
            return BottomNavigationFragment.getInstance()
        }
    }

    class TabScreen(private val tabName: String) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return TabContentFragment.getInstance(tabName)
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

    class TabContentView(private val tabName: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return TabContentFragment.getInstance(tabName)
        }
    }

    class MoreContentView(private val sectionName: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MoreSectionFragment.getInstance(sectionName)
        }
    }

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
//
//    class SelectPhotoScreen : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return SelectPhotoFragment()
//        }
}