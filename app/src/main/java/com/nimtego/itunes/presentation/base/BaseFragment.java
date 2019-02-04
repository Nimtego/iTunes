package com.nimtego.itunes.presentation.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.nimtego.itunes.presentation.utils.toast.SimpleToastAlarm;
import com.nimtego.itunes.presentation.utils.toast.ToastAlarm;

import java.util.Map;

public abstract class BaseFragment<P extends BaseContract.Presenter>
        extends Fragment
        implements BaseContract.View<P> {

    protected P mPresenter;
    protected BaseContract.View parent;


    @Override
    @SuppressWarnings("unchecked")
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseContract.View)
            this.parent = (BaseContract.View) context;
        mPresenter = supplyPresenter();
        mPresenter.attach(this);
    }

    @Override
    public void onDestroy() {
        mPresenter.detach();
        parent = null;
        super.onDestroy();
    }

    @Override
    public void runOnMainThread(Runnable runnable) {
        this.getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showLoading() {
        parent.showLoading();
    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    @Override
    public void hideLoading() {
        parent.hideLoading();
    }

    @Override
    public void toast(String message) {
        ToastAlarm toastAlarm = new SimpleToastAlarm(getActivity());
        toastAlarm.message(message);
    }

    @Override
    public void showView(Class<? super BaseContract.View> view, Map<String, String> params) {
        parent.showView(view, params);
    }

    @Override
    public void showView(Class<? super BaseContract.View> view) {
        parent.showView(view);
    }


}