package com.nimtego.itunes.presentation.utils.navigation;

import com.nimtego.itunes.presentation.information_view.InformationAlbumActivity;
import com.nimtego.itunes.presentation.main.AlbumsCollectionActivity;
import com.nimtego.itunes.presentation.main.AlbumsCollectionContract;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.information_view.InformationAlbumContract;

public final class ViewRegistry {
    private ViewRegistry() {
    }

    public static <V extends BaseContract.View> Class<?> getViewImplementation(Class<? super V> view) {
        if (view == AlbumsCollectionContract.View.class) {
            return AlbumsCollectionActivity.class;
        } else if (view == InformationAlbumContract.View.class) {
            return InformationAlbumActivity.class;
        } else {
            throw new IllegalArgumentException("Incorrect class " + view.getCanonicalName());
        }
    }
}
