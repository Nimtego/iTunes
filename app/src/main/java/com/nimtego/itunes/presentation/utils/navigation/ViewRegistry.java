package com.nimtego.itunes.presentation.utils.navigation;

import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.information_view.DetailedInformationActivity;
import com.nimtego.itunes.presentation.information_view.DetailedInformationContract;
import com.nimtego.itunes.presentation.main.MainActivity;
import com.nimtego.itunes.presentation.main.MainContract;

public final class ViewRegistry {
    private ViewRegistry() {
    }

    public static <V extends BaseContract.View> Class<?> getViewImplementation(Class<? super V> view) {
        if (view == MainContract.View.class) {
            return MainActivity.class;
        } else if (view == DetailedInformationContract.View.class) {
            return DetailedInformationActivity.class;
        } else {
            throw new IllegalArgumentException("Incorrect class " + view.getCanonicalName());
        }
    }
}
