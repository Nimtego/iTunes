package com.nimtego.itunes.presenter;

import com.nimtego.itunes.mvp_contracts.BaseContract;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

        protected V view;

        @Override
        public void attach(V view) {
            this.view = view;
        }

        @Override
        public void detach() {
            this.view = null;
        }

        @Override
        public V getView() {
            return view;
        }
}
