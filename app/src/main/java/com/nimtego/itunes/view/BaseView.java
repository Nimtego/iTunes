package com.nimtego.itunes.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nimtego.itunes.mvp_contracts.BaseContract;
import com.nimtego.itunes.utils.CommonUtils;

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
        //if (!isDestroyed())
            runOnUiThread(runnable);
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
