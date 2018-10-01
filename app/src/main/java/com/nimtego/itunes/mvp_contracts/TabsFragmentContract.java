package com.nimtego.itunes.mvp_contracts;

import com.nimtego.itunes.model.ModelManager;
import com.nimtego.itunes.service.ResultEntity;
import com.nimtego.itunes.utils.IpTags;

import java.util.List;

public interface TabsFragmentContract {
    interface Presenter<V extends View> extends BaseFragmentContract.Presenter<V> {

        void pushInRV(int position);

        void longPushInRV(int position);
    }

    interface View<P extends Presenter> extends BaseFragmentContract.View<P> {
        void setSearchList(ModelManager modelManager);
    }
}
