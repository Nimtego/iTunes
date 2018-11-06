package com.nimtego.itunes.data.entity.mapper;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityDataMapper {
    public Artist transformArtist(final ArtistResult songResult) {
        return Artist.builder()
                .artistName(songResult.getArtistName())
                .artistId(songResult.getArtistId())
                .artistViewUrl(songResult.getArtistViewUrl())
                .build();
    }

    public List<Artist> transformArtists(final Collection<ArtistResult> artistResultCollection) {
        return artistResultCollection.stream()
                .map(this::transformArtist)
                .collect(Collectors.toList());
    }
    public Album transformAlbum(final AlbumResult albumResult) {
        return Album.builder()
                .albumName(albumResult.getCollectionName())
                .albumId(albumResult.getCollectionId())
                .albumArtistName(albumResult.getArtistName())
                .albumArtWorkUrl(albumResult.getArtworkUrl100())
                .build()
                ;
    }

    public List<Album> transformAlbums(final Collection<AlbumResult> artistResultCollection) {
        return artistResultCollection.stream()
                .map(this::transformAlbum)
                .collect(Collectors.toList())
                ;
    }
    public List<Album> transformAlbums(final AlbumsRepository albumsRepository) {
        return transformAlbums(albumsRepository.getResults());
    }

    public List<Song> transformSongs(final Collection<SongResult> results) {
        return results.stream()
                .map(this::transformSong)
                .collect(Collectors.toList());
    }

    private Song transformSong(SongResult songResult) {
        return Song.builder()
                .trackName(songResult.getTrackName())
                .artistName(songResult.getArtistName())
                .build();
    }

    public List<Song> transformSongs(final SongsRepository songsRepository) {
        return transformSongs(songsRepository.getResults());
    }
}
