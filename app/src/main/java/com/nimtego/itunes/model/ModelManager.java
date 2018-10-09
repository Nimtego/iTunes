package com.nimtego.itunes.model;



import java.util.List;

public interface ModelManager {
    List<Album> getAlbums();
    List<Artist> getArtists();
    List<Song> getSongs();
}
