package com.nimtego.itunes.presentation.mvp_contracts;

import com.nimtego.itunes.domain.model.ModelManager;

public interface TabsFragmentContract {
    interface Presenter<V extends View> extends BaseFragmentContract.Presenter<V> {

        void pushInRV(int position);

        void longPushInRV(int position);
    }

    interface View<P extends Presenter> extends BaseFragmentContract.View<P> {
        void setSearchList(ModelManager modelManager);
    }
}
