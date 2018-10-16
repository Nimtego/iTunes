package com.nimtego.itunes.view;

import com.nimtego.itunes.mvp_contracts.AlbumsCollectionContract;
import com.nimtego.itunes.mvp_contracts.BaseContract;
import com.nimtego.itunes.mvp_contracts.InformationAlbumContract;

public final class ViewRegistry {
    private ViewRegistry() {
    }

    static <V extends BaseContract.View> Class<?> getViewImplementation(Class<? super V> view) {
        if (view == AlbumsCollectionContract.View.class) {
            return AlbumsCollectionActivity.class;
        } else if (view == InformationAlbumContract.View.class) {
            return InformationAlbumActivity.class;
        } else {
            throw new IllegalArgumentException("Incorrect class " + view.getCanonicalName());
        }
    }
}
