package com.nimtego.itunes.mvp_contracts;

import com.nimtego.itunes.service.ResultEntity;

import java.util.List;

public interface AlbumsCollectionContract {
    interface Presenter<V extends View> extends BaseContract.Presenter<V> {

        void search();

        void viewIsReady();

        void pushInRV(int position);

        void longPushInRV(int position);
    }

    interface View<P extends Presenter> extends BaseContract.View<P> {

        String getsearchText();

        void clearList();


        void toast(String message);

        void intent(String tabType);

        void setSearchList(List<ResultEntity> list);
    }
}
