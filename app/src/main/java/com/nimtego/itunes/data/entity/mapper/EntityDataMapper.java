package com.nimtego.itunes.data.entity.mapper;

import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.AlbumsRepository;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.data.rest.pojo.ArtistsRepository;
import com.nimtego.itunes.data.rest.pojo.SongResult;
import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.presentation.main.model.AlbumModel;
import com.nimtego.itunes.presentation.main.model.ArtistModel;
import com.nimtego.itunes.presentation.main.model.SongModel;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityDataMapper {
    private ArtistModel transformArtist(final ArtistResult result) {
        return ArtistModel.builder()
                .artistName(result.getArtistName())
                .primaryGenreName(result.getPrimaryGenreName())
                .artistViewUrl(result.getArtistLinkUrl())
                .build();
    }

    private List<ArtistModel> transformArtists(final Collection<ArtistResult> artistResultCollection) {
        return artistResultCollection.stream()
                .map(this::transformArtist)
                .collect(Collectors.toList());
    }

    public List<ArtistModel> transformArtists(final ArtistsRepository artistsRepository) {
        return transformArtists(artistsRepository.getResults());
    }
    private AlbumModel transformAlbum(final AlbumResult result) {
        return AlbumModel.builder()
                .albumName(result.getCollectionName())
                .albumId(result.getCollectionId() == null ? 0 : result.getCollectionId())
                .albumArtistName(result.getArtistName())
                .albumArtWorkUrl(result.getArtworkUrl100())
                .build()
                ;
    }

    private List<AlbumModel> transformAlbums(final Collection<AlbumResult> results) {
        return results.stream()
                .map(this::transformAlbum)
                .collect(Collectors.toList())
                ;
    }
    public List<AlbumModel> transformAlbums(final AlbumsRepository albumsRepository) {
        return transformAlbums(albumsRepository.getResults());
    }

    private List<SongModel> transformSongs(final Collection<SongResult> results) {
        return results.stream()
                .map(this::transformSong)
                .collect(Collectors.toList());
    }

    private SongModel transformSong(SongResult songResult) {
        return SongModel.builder()
                .trackName(songResult.getTrackName())
                .trackArtistName(songResult.getArtistName())
                .trackArtwork(songResult.getArtworkUrl100())
                .trackAlbumName(songResult.getCollectionName())
                .build();
    }

    public List<SongModel> transformSongs(final SongsRepository songsRepository) {
        return transformSongs(songsRepository.getResults());
    }
}
