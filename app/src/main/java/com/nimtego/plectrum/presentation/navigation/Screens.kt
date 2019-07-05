package com.nimtego.plectrum.presentation.navigation

import android.content.Context
import ru.terrakok.cicerone.android.support.SupportAppScreen
import android.content.Intent
import android.support.v4.app.Fragment
import com.nimtego.plectrum.presentation.ui.fragment.BottomNavigationFragment
import com.nimtego.plectrum.presentation.ui.activity.AppActivity
import com.nimtego.plectrum.presentation.ui.fragment.MoreSectionFragment
import com.nimtego.plectrum.presentation.ui.fragment.TabContentFragment


object Screens {

//    class TabScreen(private val tabName: String) : SupportAppScreen() {
//
//        override fun getFragment(): Fragment {
//            return TabContentFragment.getInstance(tabName)
//        }
//    }
//
//    class StartScreen : SupportAppScreen() {
//        override fun getActivityIntent(context: Context): Intent {
//            return Intent(context, AppActivity::class.java)
//        }
//    }
//
//    class MainScreen : SupportAppScreen() {
//        override fun getActivityIntent(context: Context): Intent {
//            return Intent(context, AppActivity::class.java)
//        }
//    }

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

    object BottomNavigationView : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BottomNavigationFragment.getInstance()
        }
    }

    class TabContentView(private val tabName: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return TabContentFragment.getInstance(tabName)
        }
    }

    object MusicTabContentView : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return TabContentFragment.getInstance("MUSIC_TAB")
        }
    }

    object MovieTabContentView : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return TabContentFragment.getInstance("MOVIE_TAB")
        }
    }

    object BookTabContentView : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return TabContentFragment.getInstance("BOOK_TAB")
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