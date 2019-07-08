package com.nimtego.plectrum.presentation.navigation

import android.content.Context
import ru.terrakok.cicerone.android.support.SupportAppScreen
import android.content.Intent
import android.support.v4.app.Fragment
import com.nimtego.plectrum.presentation.ui.activity.AppActivity
import com.nimtego.plectrum.presentation.ui.fragment.*


object Screens {

    object BottomNavigationScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BottomNavigationFragment.getInstance()
        }
    }

    object MusicTabNavigationScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MusicTabNavFragment.getInstance()
        }
    }

    object MusicTabScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MusicTabFragment.getInstance()
        }
    }

    object MovieTabNavigationScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MovieTabNavFragment.getInstance()
        }
    }

    object MovieTabScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MovieTabFragment.getInstance()
        }
    }

    object BookTabNavigationScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BookTabNavFragment.getInstance()
        }
    }

    object BookTabScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BookTabFragment.getInstance()
        }
    }

//    class TabContentView(private val tabName: String) : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return TabContentFragment.getInstance(tabName)
//        }
//    }
//
//    object MusicTabContentView : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return TabContentFragment.getInstance("MUSIC_TAB")
//        }
//    }
//
//    object MovieTabContentView : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return TabContentFragment.getInstance("MOVIE_TAB")
//        }
//    }
//
//    object BookTabContentView : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return TabContentFragment.getInstance("BOOK_TAB")
//        }
//    }

    class MoreContentView(private val sectionName: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MoreSectionFragment.getInstance(sectionName)
        }
    }
}