package com.nimtego.itunes.data.mapper;

import com.nimtego.itunes.model.Artist;
import com.nimtego.itunes.service.pojo.ArtistResult;
import com.nimtego.itunes.service.pojo.SongResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.NonNull;

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
