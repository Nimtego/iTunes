package com.nimtego.plectrum.presentation.navigation

import android.support.v4.app.Fragment
import com.nimtego.plectrum.presentation.ui.fragment.*
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screens {

    object BottomNavigationScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BottomNavigationFragment.getInstance()
        }
    }

    object MusicTabNavigationScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MusicTabNavFragment.getInstance()
        }
    }

    object MusicTabScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MusicTabFragment.getInstance()
        }
    }

    object MovieTabNavigationScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MovieTabNavFragment.getInstance()
        }
    }

    object MovieTabScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MovieTabFragment.getInstance()
        }
    }

    object BookTabNavigationScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BookTabNavFragment.getInstance()
        }
    }

    object BookTabScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BookTabFragment.getInstance()
        }
    }

    //todo create User choice manager

    object MoreContentScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MoreSectionFragment.getInstance("New songs")
        }
    }

//    class MoreContentScreen(private val sectionName: String) : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return MoreSectionFragment.getInstance(sectionName)
//        }
//    }
}