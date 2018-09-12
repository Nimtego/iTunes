package com.nimtego.itunes.view;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.mvp_contracts.AlbumsCollectionContract;
import com.nimtego.itunes.presenter.AlbumsCollectionPresenter;
import com.nimtego.itunes.service.ResultEntity;
import com.nimtego.itunes.utils.RecyclerItemClickListener;
import com.nimtego.itunes.view.toast.SimpleToastAlarm;
import com.nimtego.itunes.view.toast.ToastAlarm;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumsCollectionActivity
        extends BaseView<AlbumsCollectionContract.Presenter>
        implements AlbumsCollectionContract.View<AlbumsCollectionContract.Presenter>{

    @BindView(R.id.search_edit_text)
    EditText searchText;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.search_button)
    ImageButton searchButton;

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
       // LinearLayoutManager llm = new LinearLayoutManager(this);
       // llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                toast("Single Click on position:\n" + position);
                                mPresenter.pushInRV(position);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                toast("Long press on position:\n" + position);
                                mPresenter.longPushInRV(position);
                            }
                        })
        );
    }
    private void init() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.search();
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
    public void toast(String message) {
        ToastAlarm toastAlarm = new SimpleToastAlarm(this);
        toastAlarm.message(message);
    }

    @Override
    public void intent(String tabType) {

    }

    @Override
    public void setSearchList(List<ResultEntity> list) {
        RecyclerView.Adapter adapter = new PostAdapter(list, this);
        mRecyclerView.setAdapter(adapter);
    }
}
