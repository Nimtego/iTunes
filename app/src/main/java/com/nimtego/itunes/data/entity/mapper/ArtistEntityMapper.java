package com.nimtego.itunes.data.entity.mapper;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistEntityMapper {
    public Artist transform(final ArtistResult songResult) {
        return Artist.builder()
                .artistName(songResult.getArtistName())
                .artistId(songResult.getArtistId())
                .artistViewUrl(songResult.getArtistViewUrl())
                .build();
    }

    public List<Artist> transformArtists(final Collection<ArtistResult> artistResultCollection) {
        return artistResultCollection.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
    public Album transform(final AlbumResult songResult) {
/*        return Album.builder()
                .artistName(songResult.getArtistName())
                .artistId(songResult.getArtistId())
                .artistViewUrl(songResult.getArtistViewUrl())
                .build();*/
        return Album.builder().build(); // TODO: 17.10.2018
    }

    public List<Album> transformAlbums(final Collection<AlbumResult> artistResultCollection) {
/*        return artistResultCollection.stream()
                .map(this::transform)
                .collect(Collectors.toList());*/
        return new ArrayList<>(); // TODO: 17.10.2018
    }
}
