package com.nimtego.plectrum.presentation.navigation

import android.support.v4.app.Fragment
import com.nimtego.plectrum.presentation.ui.fragment.detail.AlbumDetailFragment
import com.nimtego.plectrum.presentation.ui.fragment.detail.ArtistDetailFragment
import com.nimtego.plectrum.presentation.ui.fragment.detail.SongDetailFragment
import com.nimtego.plectrum.presentation.ui.fragment.general.SplashFragment
import com.nimtego.plectrum.presentation.ui.fragment.navigation.*
import com.nimtego.plectrum.presentation.ui.fragment.popular.*
import com.nimtego.plectrum.presentation.ui.fragment.search.SearchContentFragment
import com.nimtego.plectrum.presentation.ui.fragment.search.SearchInformationFragment
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

    class ArtistDetailScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ArtistDetailFragment.getInstance(navigationQualifier)
        }
    }

    class AlbumDetailScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AlbumDetailFragment.getInstance(navigationQualifier)
        }
    }

    class SongDetailScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SongDetailFragment.getInstance(navigationQualifier)
        }
    }

    class ItemInformationScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return InformationFragment.getInstance(navigationQualifier)
        }
    }

    class SearchContentScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchContentFragment.getInstance(navigationQualifier)
        }
    }

    class SearchNavTabScreen(val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchTabNavFragment.getInstance(navigationQualifier)
        }
    }

    class SearchNavigationScreen(val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchNavigationFragment.getInstance(navigationQualifier)
        }
    }

    class SearchItemInformationScreen(private val navigationQualifier: String) : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SearchInformationFragment.getInstance(navigationQualifier)
        }
    }
}