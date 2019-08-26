package com.nimtego.plectrum.presentation.navigation

import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.di.modules.navigation.NavigationQualifiers
import com.nimtego.plectrum.presentation.manger.ResourceManager
import ru.terrakok.cicerone.android.support.SupportAppScreen
import javax.inject.Inject

class SearchTabScreenFabric @Inject constructor(
        resourceManager: ResourceManager
) {
    private val musicScreensContainer: ScreenTabContainer<SupportAppScreen>
    private val movieScreensContainer: ScreenTabContainer<SupportAppScreen>
    private val booksScreensContainer: ScreenTabContainer<SupportAppScreen>

    init {
//todo specific screen
        this.musicScreensContainer = SearchTabScreensContainer(
                mapOf(
                        Tab(0 to resourceManager.getString(R.string.music_text_search_tab_track))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.music_text_search_tab_track)),
                        Tab(1 to resourceManager.getString(R.string.music_text_search_tab_album))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.music_text_search_tab_album)),
                        Tab(2 to resourceManager.getString(R.string.music_text_search_tab_author))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.music_text_search_tab_author)))

        )

        this.movieScreensContainer = SearchTabScreensContainer(
                mapOf(
                        Tab(0 to resourceManager.getString(R.string.movie_text_search_tab_movie))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.movie_text_search_tab_movie)),
                        Tab(1 to resourceManager.getString(R.string.movie_text_search_tab_director))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.movie_text_search_tab_director)),
                        Tab(2 to resourceManager.getString(R.string.movie_text_search_tab_artist))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.movie_text_search_tab_artist)))

        )

        this.booksScreensContainer = SearchTabScreensContainer(
                mapOf(
                        Tab(0 to resourceManager.getString(R.string.book_text_search_tab_book))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.book_text_search_tab_book)),
                        Tab(1 to resourceManager.getString(R.string.book_text_search_tab_audio_book))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.book_text_search_tab_audio_book)),
                        Tab(2 to resourceManager.getString(R.string.book_text_search_tab_author))
                                to Screens.SearchNavTabScreen(resourceManager.getString(R.string.book_text_search_tab_author)))

        )
    }

    fun getScreensContainer(navigationQualifier: String): ScreenTabContainer<SupportAppScreen> {
        //todo exception
        return when(navigationQualifier) {
            NavigationQualifiers.TAB_MUSIC_NAVIGATION -> this.musicScreensContainer
            NavigationQualifiers.TAB_MOVIE_NAVIGATION -> this.movieScreensContainer
            NavigationQualifiers.TAB_BOOK_NAVIGATION  -> this.booksScreensContainer
            else -> throw Exception(navigationQualifier)
        }
    }
}