package com.nimtego.itunes.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.mvp_contracts.AlbumsCollectionContract;
import com.nimtego.itunes.presenter.AlbumsCollectionPresenter;
import com.nimtego.itunes.service.ResultEntity;
import com.nimtego.itunes.utils.IpTags;
import com.nimtego.itunes.utils.RecyclerItemClickListener;
import com.nimtego.itunes.view.toast.SimpleToastAlarm;
import com.nimtego.itunes.view.toast.ToastAlarm;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsCollectionActivity
        extends BaseView<AlbumsCollectionContract.Presenter>
        implements AlbumsCollectionContract.View<AlbumsCollectionContract.Presenter> {

    @BindView(R.id.search_edit_text)
    EditText searchText;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums_collection);
        ButterKnife.bind(this);
        init();
        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        LinearLayoutManager ll = new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                mPresenter.pushInRV(position);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                mPresenter.longPushInRV(position);
                            }
                        })
        );
    }

    private void init() {
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mPresenter.search();
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (in != null) {
                        in.hideSoftInputFromWindow(searchText.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public AlbumsCollectionContract.Presenter supplyPresenter() {
        return new AlbumsCollectionPresenter();
    }

    @Override
    public String getsearchText() {
        return String.valueOf(searchText.getText());
    }

    @Override
    public void clearList() {

    }

    @Override
    public void intent(IpTags tags, String id) {
        Intent intent = new Intent(this, mPresenter.getNextActivity());
        intent.putExtra(tags.toString(), id);
        (this).startActivity(intent);
    }

    @Override
    public void setSearchList(List<ResultEntity> list) {
        RecyclerView.Adapter adapter = new PostAdapter(list, this);
        mRecyclerView.setAdapter(adapter);
    }
}
