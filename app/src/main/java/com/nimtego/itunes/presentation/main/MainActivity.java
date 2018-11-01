package com.nimtego.itunes.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.data.rest.pojo.AlbumResult;
import com.nimtego.itunes.presentation.base.BaseView;
import com.nimtego.itunes.presentation.main.adapter.AlbumAdapter;
import com.nimtego.itunes.presentation.main.adapter.PostAdapter;
import com.nimtego.itunes.presentation.main.adapter.ViewPagerAdapter;
import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.model.AlbumModel;

import java.util.Collection;
import java.util.List;


public class MainActivity extends BaseView<AlbumsCollectionContract.Presenter>
        implements AlbumsCollectionContract.View<AlbumsCollectionContract.Presenter> {
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
        setupViewPager();

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new MainTabsFragment(), "Albums");
        mViewPagerAdapter.addFragment(new MainTabsFragment(), "Artists");
        mViewPagerAdapter.addFragment(new MainTabsFragment(), "Songs");
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public AlbumsCollectionContract.Presenter supplyPresenter() {
        return new AlbumsCollectionPresenter();
    }

    @Override
    public String getSearchText() {
        return String.valueOf(searchText.getText());
    }

    @Override
    public void render(List<AlbumModel> list) {
        MainTabsFragment fragment =
                (MainTabsFragment) mViewPagerAdapter
                        .getItem(mViewPager.getCurrentItem());
        fragment.setSearchList(list);
    }
}