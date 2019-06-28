package com.nimtego.plectrum.presentation.mapper;

import com.nimtego.plectrum.data.entity.Album;
import com.nimtego.plectrum.presentation.old.main.model.AlbumModel;

import org.junit.Before;
import org.junit.Test;

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


    @Before
    public void setUp() {
    }

    @Test
    public void transformAlbumTest() {
        Album album = createFakeAlbum();


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
    }
}