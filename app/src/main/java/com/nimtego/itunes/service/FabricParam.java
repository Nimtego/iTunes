package com.nimtego.itunes.service;

import java.util.HashMap;
import java.util.Map;

public class FabricParam {
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
        return searchAlbumParam(album, 20);
    }
}
