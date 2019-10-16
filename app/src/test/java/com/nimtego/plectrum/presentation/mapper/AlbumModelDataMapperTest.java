package com.nimtego.plectrum.presentation.mapper;

import com.nimtego.plectrum.presentation.mvp.model.music.AlbumDetailModel;

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

//    @Test
//    public void transformAlbumTest() {
//        Album album = createFakeAlbum();
//
//
//    }

    private void testAssert(AlbumDetailModel albumDetailModel) {
        assertThat(albumDetailModel, is(instanceOf(AlbumDetailModel.class)));
        assertThat(albumDetailModel.getAlbumArtistName(), is(ALBUM_ARTIST_NAME));
        assertThat(albumDetailModel.getAlbumName(), is(ALBUM_NAME));
        assertThat(albumDetailModel.getAlbumArtwork(), is(ALBUM_IMAGE));
    }


    @Test
    public void transformAlbums() {
    }
}