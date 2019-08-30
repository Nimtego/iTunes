package com.nimtego.plectrum.presentation.navigation

import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.ResourceManager
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class SearchTabScreenFabric @Inject constructor(
        private val resourceManager: ResourceManager
) {
    private var musicScreensContainer: SearchTabScreensContainer? = null
    private var movieScreensContainer: SearchTabScreensContainer? = null
    private var booksScreensContainer: SearchTabScreensContainer? = null

    private fun musicScreensContainer(navigationQualifier: String): SearchTabScreensContainer {
        return this.musicScreensContainer ?: run {
            this.musicScreensContainer = SearchTabScreensContainer(
                    mapOf(
                            Tab(0 to resourceManager.getString(R.string.music_text_search_tab_track))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.music_text_search_tab_track)
                            ),
                            Tab(1 to resourceManager.getString(R.string.music_text_search_tab_album))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.music_text_search_tab_album)
                            ),
                            Tab(2 to resourceManager.getString(R.string.music_text_search_tab_author))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.music_text_search_tab_author))
                    ))
            return this.musicScreensContainer!!
        }
    }


    private fun movieScreensContainer(navigationQualifier: String): SearchTabScreensContainer {
        return this.movieScreensContainer ?: run {
            this.movieScreensContainer = SearchTabScreensContainer(
                    mapOf(
                            Tab(0 to resourceManager.getString(R.string.movie_text_search_tab_movie))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.movie_text_search_tab_movie)
                            ),
                            Tab(1 to resourceManager.getString(R.string.movie_text_search_tab_director))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.movie_text_search_tab_director)
                            ),
                            Tab(2 to resourceManager.getString(R.string.movie_text_search_tab_artist))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.movie_text_search_tab_artist)
                            ))

            )
            return this.movieScreensContainer!!
        }
    }

    private fun booksScreensContainer(navigationQualifier: String): SearchTabScreensContainer {
        return this.booksScreensContainer ?: run {
            this.booksScreensContainer = SearchTabScreensContainer(
                    mapOf(
                            Tab(0 to resourceManager.getString(R.string.book_text_search_tab_book))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.book_text_search_tab_book)
                            ),
                            Tab(1 to resourceManager.getString(R.string.book_text_search_tab_audio_book))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.book_text_search_tab_audio_book)
                            ),
                            Tab(2 to resourceManager.getString(R.string.book_text_search_tab_author))
                                    to Screens.SearchNavTabScreen(
                                    navigationQualifier + resourceManager.getString(R.string.book_text_search_tab_author)
                            ))

            )
            return this.booksScreensContainer!!
        }
    }

    fun getScreensContainer(navigationQualifier: String): ScreenTabContainer<SupportAppScreen> {
        return when (navigationQualifier) {
            NavigationQualifiers.TAB_MUSIC_NAVIGATION -> musicScreensContainer(navigationQualifier)
            NavigationQualifiers.TAB_MOVIE_NAVIGATION -> movieScreensContainer(navigationQualifier)
            NavigationQualifiers.TAB_BOOK_NAVIGATION -> booksScreensContainer(navigationQualifier)
            else -> throw Exception("$navigationQualifier is not supported")
        }
    }
}