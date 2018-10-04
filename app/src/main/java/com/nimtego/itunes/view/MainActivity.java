package com.nimtego.itunes.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.mvp_contracts.AlbumsCollectionContract;
import com.nimtego.itunes.presenter.AlbumsCollectionPresenter;
import com.nimtego.itunes.service.pojo.AlbumResult;

import com.nimtego.itunes.utils.IpTags;
import com.nimtego.itunes.view.adapter.ViewPagerAdapter;
import com.nimtego.itunes.view.fragments.MainTabsFragment;

import java.util.List;


public class MainActivity extends BaseView<AlbumsCollectionContract.Presenter>
        implements AlbumsCollectionContract.View<AlbumsCollectionContract.Presenter>{
    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private EditText searchText;
    private ViewPagerAdapter mViewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_edit_text);
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

        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new MainTabsFragment(), "Albums");
        mViewPagerAdapter.addFragment(new MainTabsFragment(), "Artists");
        mViewPagerAdapter.addFragment(new MainTabsFragment(), "Songs");
        viewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public AlbumsCollectionContract.Presenter supplyPresenter() {
        return new AlbumsCollectionPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
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
    public void setSearchList(List<AlbumResult> list) {
        RecyclerView.Adapter adapter = new PostAdapter(list, this);
        MainTabsFragment f = (MainTabsFragment) mViewPagerAdapter.getItem(mViewPager.getCurrentItem());
        f.setSearchList(list);
    }
}