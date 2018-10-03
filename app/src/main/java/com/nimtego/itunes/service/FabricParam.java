package com.nimtego.itunes.service;

import java.util.HashMap;
import java.util.Map;

public class FabricParam {

    private static final int limit = 200;

    public static Map<String, String> searchAlbumParam(String album, int limit){
        /*
         https://itunes.apple.com/search?term=metallica&entity=album&limit=5&attribute=albumTerm
         */
        Map<String, String> param = new HashMap<>();
        param.put("term", album);
        param.put("entity", "album");
        param.put("limit", String.valueOf(limit));
        param.put("attribute", "albumTerm");
        return param;
    }
    public static Map<String, String> searchAlbumParam(String album){
        return searchAlbumParam(album, limit);
    }

    //https://itunes.apple.com/lookup?id=579372950&entity=song
    public static Map<String, String> lookupSongsAlbum(String album){
        Map<String, String> param = new HashMap<>();
        param.put("id", album);
        param.put("entity", "song");
        return param;
    }

    public static Map<String, String> searchSongParam(String song) {
         /*
         https://itunes.apple.com/search?media=music&entity=musicTrack&attribute=songTerm&term=xxx
         */
        Map<String, String> param = new HashMap<>();
        param.put("term", song);
        param.put("entity", "musicTrack");
        param.put("limit", String.valueOf(limit));
        param.put("attribute", "songTerm");
        return param;
    }

    public static Map<String, String> searchArtistParam(String artist) {
         /*
         https://itunes.apple.com/search?term=artist
         */
        Map<String, String> param = new HashMap<>();
        param.put("term", artist);
/*        param.put("entity", "musicTrack");*/
        param.put("limit", String.valueOf(limit));
/*        param.put("attribute", "songTerm");*/
        return param;
    }

}
