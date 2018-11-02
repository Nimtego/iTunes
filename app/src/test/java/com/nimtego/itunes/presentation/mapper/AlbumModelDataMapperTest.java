package com.nimtego.itunes.presentation.mapper;

import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


public class AlbumModelDataMapperTest {
    private static final String ALBUM_NAME = "AlbumName";
    private static final int ALBUM_ID = 1;
    private static final String ALBUM_IMAGE = "Image";
    private static final int ALBUM_ARTIST_ID = 2;
    private static final String ALBUM_ARTIST_NAME = "ArtistName";
    private static final double ALBUM_PRICE = 2.5;

    private AlbumModelDataMapper mapper;

    @Before
    public void setUp() {
        mapper = new AlbumModelDataMapper();
    }

    @Test
    public void transformAlbumTest() {
        Album album = createFakeAlbum();
        AlbumModel albumModel = mapper.transform(album);

        testAssert(albumModel);

    }

    private void testAssert(AlbumModel albumModel) {
        assertThat(albumModel, is(instanceOf(AlbumModel.class)));
        assertThat(albumModel.getAlbumArtistName(), is(ALBUM_ARTIST_NAME));
        assertThat(albumModel.getAlbumName(), is(ALBUM_NAME));
        assertThat(albumModel.getAlbumArtwork(), is(ALBUM_IMAGE));
    }


    private Album createFakeAlbum() {
        return Album.builder()
                .albumName(ALBUM_NAME)
                .albumId(ALBUM_ID)
                .albumArtWorkUrl(ALBUM_IMAGE)
                .albumArtistId(ALBUM_ARTIST_ID)
                .albumArtistName(ALBUM_ARTIST_NAME)
                .albumPrice(ALBUM_PRICE)
                .build();
    }

    @Test
    public void transformAlbums() {
        mapper.transformAlbums(Stream
                .generate(this::createFakeAlbum)
                .limit(5)
                .collect(Collectors.toList()))
                .forEach(this::testAssert);
    }
}