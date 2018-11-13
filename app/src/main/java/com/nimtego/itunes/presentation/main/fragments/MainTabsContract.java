package com.nimtego.itunes.presentation.main.fragments;

import com.nimtego.itunes.presentation.base.BaseContract;

public interface MainTabsContract {
    interface Presenter<V extends View, I extends BaseContract.Interactor>
            extends BaseContract.Presenter<V, I>{

    }
    interface View<P extends Presenter> extends BaseContract.View<P>{
        void search(String response);
        boolean isRvEmpty();
        String getCurrentSerch();
        void setCurrentSearch(String response);
    }
}
