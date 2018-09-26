package com.nimtego.itunes.mvp_contracts;

import com.nimtego.itunes.service.ResultEntity;
import com.nimtego.itunes.utils.IpTags;

import java.util.List;

public interface AlbumsCollectionContract {
    interface Presenter<V extends View> extends BaseContract.Presenter<V> {

        void search();

        void pushInRV(int position);

        void longPushInRV(int position);

        void viewIsReady();
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

        String getsearchText();

        void clearList();

        void intent(IpTags tags, String id);

        void setSearchList(List<ResultEntity> list);
    }
}
