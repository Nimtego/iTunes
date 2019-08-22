package com.nimtego.plectrum.presentation.navigation

import android.support.v4.app.Fragment
import com.nimtego.plectrum.presentation.ui.fragment.general.InformationFragment
import com.nimtego.plectrum.presentation.ui.fragment.general.MoreSectionFragment
import com.nimtego.plectrum.presentation.ui.fragment.general.SplashFragment
import com.nimtego.plectrum.presentation.ui.fragment.navigation.BookTabNavFragment
import com.nimtego.plectrum.presentation.ui.fragment.navigation.BottomNavigationFragment
import com.nimtego.plectrum.presentation.ui.fragment.navigation.MovieTabNavFragment
import com.nimtego.plectrum.presentation.ui.fragment.navigation.MusicTabNavFragment
import com.nimtego.plectrum.presentation.ui.fragment.popular.BookTabFragment
import com.nimtego.plectrum.presentation.ui.fragment.popular.MovieTabFragment
import com.nimtego.plectrum.presentation.ui.fragment.popular.MusicTabFragment
import com.nimtego.plectrum.presentation.ui.fragment.search.SearchContentFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen


object Screens {

    object SplashScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SplashFragment.getInstance()
        }
    }

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

//    object MoreContentScreen : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return MoreSectionFragment.getInstance("New songs")
//        }
//    }

//    object ItemInformationScreen : SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return InformationFragment.getInstance()
//        }
//    }

    class MoreContentScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return MoreSectionFragment.getInstance(navigationQualifier)
        }
    }

    class ItemInformationScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return InformationFragment.getInstance(navigationQualifier)
        }
    }

    class SearchScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchContentFragment.getInstance(navigationQualifier)
        }
    }
}