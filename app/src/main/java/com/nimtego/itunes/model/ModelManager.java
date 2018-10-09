package com.nimtego.itunes.model;



import com.nimtego.itunes.data.entity.Album;
import com.nimtego.itunes.data.entity.Artist;
import com.nimtego.itunes.data.entity.Song;

import java.util.List;

public interface ModelManager {
    List<Album> getAlbums();
    List<Artist> getArtists();
    List<Song> getSongs();
}
