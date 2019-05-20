package com.nimtego.plectrum.data.rest.network;

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
        param.put("limit", String.valueOf(limit));
        return param;
    }

    //https://itunes.apple.com/lookup?id=579372950&entity=album
    public static Map<String, String> lookupAlbum(String album){
        Map<String, String> param = new HashMap<>();
        param.put("id", album);
        param.put("entity", "song");
        param.put("limit", String.valueOf(limit));
        return param;
    }

    public static Map<String, String> searchSongParam(String song) {
         /*
         https://itunes.apple.com/search?media=music&entity=musicTrack&limit=5&attribute=songTerm&term=xxx
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
         https://itunes.apple.com/search?media=music&entity=musicArtist&limit=5&attribute=artistTerm&term=xxxx
         */
        Map<String, String> param = new HashMap<>();
        param.put("media", "music");
        param.put("entity", "musicArtist");
        param.put("attribute", "artistTerm");
        param.put("term", artist);
        param.put("limit", String.valueOf(limit));
        return param;
    }

    public static Map<String,String> searchWikiInf(String response) {
        /*
        https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles=XXXX
         */

        /*
        https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=XXX
         */
        Map<String, String> param = new HashMap<>();
        param.put("format", "json");
        param.put("action", "query");
        param.put("list", "search");
        param.put("srsearch", response);
        return param;

    }

    public static Map<String,String> lookupArtist(String s) {
        Map<String, String> param = new HashMap<>();
        param.put("id", s);
        param.put("entity", "musicArtist");
        return param;
    }
}
