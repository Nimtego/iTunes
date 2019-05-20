package com.nimtego.plectrum.presentation.utils.navigation;

import com.nimtego.plectrum.presentation.base.BaseContract;
import com.nimtego.plectrum.presentation.information_view.DetailedInformationActivity;
import com.nimtego.plectrum.presentation.information_view.DetailedInformationContract;
import com.nimtego.plectrum.presentation.main.MainActivity;
import com.nimtego.plectrum.presentation.main.MainContract;

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
