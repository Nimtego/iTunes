package com.nimtego.plectrum.data.repository.repository

import com.nimtego.plectrum.data.model.itunes.SongResult
import com.nimtego.plectrum.data.model.mappers.MusicalContentMapper
import com.nimtego.plectrum.domain.repository.AlbumSource
import com.nimtego.plectrum.domain.repository.AuthorSource
import com.nimtego.plectrum.domain.repository.SongSource
import com.nimtego.plectrum.presentation.mvp.model.song.Album
import com.nimtego.plectrum.presentation.mvp.model.song.Author
import com.nimtego.plectrum.presentation.mvp.model.song.Song
import io.reactivex.Observable

class MusicalRepository(
        private val songDataStoreFactory: SongSource<SongResult>,
        private val albumDataStoreFactory: AlbumSource,
        private val authorDataStoreFactory: AuthorSource,
        private val mapper: MusicalContentMapper
) : SongSource<Song> {

    override fun getSongsByRequest(request: String): Observable<List<Song>> {
        return songDataStoreFactory.getSongsByRequest("Metallica").map {
            it.map { songResult ->
                mapper.songResultToSong(songResult)
            }
        }
    }

    override fun getSongsByAlbumId(id: Int): Observable<List<Song>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSongById(id: Int): Observable<Song> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumsByRequest(request: String): Observable<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumsByAuthorId(id: Int): Observable<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumBySongId(id: Int): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlbumById(id: Int): Observable<Album> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorByRequest(request: String): Observable<List<Author>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorByAlbumId(id: Int): Observable<Author> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAuthorBySongId(id: Int): Observable<Author> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}