package com.nimtego.itunes.presentation.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nimtego.itunes.presentation.utils.CommonUtils;
import com.nimtego.itunes.presentation.utils.navigation.ViewRegistry;
import com.nimtego.itunes.presentation.utils.toast.SimpleToastAlarm;
import com.nimtego.itunes.presentation.utils.toast.ToastAlarm;

import java.util.Collections;
import java.util.Map;

public abstract class BaseView<P extends BaseContract.Presenter>
        extends AppCompatActivity
        implements BaseContract.View<P> {

    protected P mPresenter;
    private ProgressDialog mProgressDialog;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = supplyPresenter();
        mPresenter.attach(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        super.onDestroy();
    }

    @Override
    public void runOnMainThread(Runnable runnable) {
        runOnUiThread(runnable);
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public Context context() {
        return this.getApplicationContext();
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void toast(String message) {
        ToastAlarm toastAlarm = new SimpleToastAlarm(this);
        toastAlarm.message(message);
    }

    @Override
    public void showView(Class<? super BaseContract.View> view, Map<String, String> params) {
        Intent intent = new Intent(this, ViewRegistry.getViewImplementation(view));
        for (Map.Entry<String, String> pair : params.entrySet()) {
            intent.putExtra(pair.getKey(), pair.getValue());
        }
        (this).startActivity(intent);
    }

    @Override
    public void showView(Class<? super BaseContract.View> view) {
        showView(view, Collections.<String, String>emptyMap());
    }
}
