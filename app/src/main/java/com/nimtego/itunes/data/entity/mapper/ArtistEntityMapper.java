package com.nimtego.itunes.data.entity.mapper;

import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.rest.pojo.ArtistResult;

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

    public List<Artist> transform(final Collection<ArtistResult> artistResultCollection) {
        return artistResultCollection.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
}
