package com.nimtego.itunes.presentation.utils.navigation;

import com.nimtego.itunes.presentation.information_view.InformationAlbumActivity;
import com.nimtego.itunes.presentation.main.AlbumsCollectionContract;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.information_view.InformationAlbumContract;
import com.nimtego.itunes.presentation.main.MainActivity;

public final class ViewRegistry {
    private ViewRegistry() {
    }

    public static <V extends BaseContract.View> Class<?> getViewImplementation(Class<? super V> view) {
        if (view == AlbumsCollectionContract.View.class) {
            return MainActivity.class;
        } else if (view == InformationAlbumContract.View.class) {
            return InformationAlbumActivity.class;
        } else {
            throw new IllegalArgumentException("Incorrect class " + view.getCanonicalName());
        }
    }
}
