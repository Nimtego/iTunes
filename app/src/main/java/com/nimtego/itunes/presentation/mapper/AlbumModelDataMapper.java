package com.nimtego.itunes.presentation.mapper;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumModelDataMapper {

    public AlbumModel transform(final Album album) {
        return AlbumModel.builder()
                .albumArtistName(album.getAlbumArtistName())
                .albumName(album.getAlbumName())
                .albumArtwork(album.getAlbumArtWorkUrl())
                .build()
                ;
    }

    public List<AlbumModel> transformAlbums(final Collection<Album> albums) {
        return albums.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
}
