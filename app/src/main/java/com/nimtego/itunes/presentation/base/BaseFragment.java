package com.nimtego.itunes.presentation.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.main.MainContract;
import com.nimtego.itunes.presentation.utils.CommonUtils;
import com.nimtego.itunes.presentation.utils.navigation.ViewRegistry;
import com.nimtego.itunes.presentation.utils.toast.SimpleToastAlarm;
import com.nimtego.itunes.presentation.utils.toast.ToastAlarm;

import java.util.Collections;
import java.util.Map;

public abstract class BaseFragment<P extends BaseContract.Presenter>
        extends Fragment
        implements BaseContract.View<P> {

    protected P mPresenter;
    private ProgressDialog mProgressDialog;
    private MainContract.View parent;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.parent = (MainContract.View) context;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        ((MainContract.View)this.getActivity()).hideLoading();
    }

    @Override
    public void toast(String message) {
        ToastAlarm toastAlarm = new SimpleToastAlarm(getActivity());
        toastAlarm.message(message);
    }

    @Override
    public void showView(Class<? super BaseContract.View> view, Map<String, String> params) {
        Intent intent = new Intent(getActivity(), ViewRegistry.getViewImplementation(view));
        for (Map.Entry<String, String> pair : params.entrySet()) {
            intent.putExtra(pair.getKey(), pair.getValue());
        }
        getActivity().startActivity(intent);
    }

    @Override
    public void showView(Class<? super BaseContract.View> view) {
        showView(view, Collections.<String, String>emptyMap());
    }


}