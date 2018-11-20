package com.nimtego.itunes.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.data.entity.Song;
import com.nimtego.itunes.presentation.base.BaseView;
import com.nimtego.itunes.presentation.main.adapter.MainPagerAdapter;
import com.nimtego.itunes.presentation.main.albums.AlbumTabsFragment;
import com.nimtego.itunes.presentation.main.artists.ArtistTabsFragment;
import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.songs.SongTabsFragment;

import java.lang.reflect.Field;


public class MainActivity extends BaseView<MainContract.Presenter>
        implements MainContract.View<MainContract.Presenter> {

    final static String KEY_SEARCH = "key_search";

    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SearchView searchText;
    private MainPagerAdapter mViewPagerAdapter;
    private ProgressBar pb;
    private String search = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_edit_text);
        if (savedInstanceState != null) {
            search = savedInstanceState.getString(KEY_SEARCH);
        }

        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.viewpager);
        setupViewPager(savedInstanceState);

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);

        pb = findViewById(R.id.progressBar);
        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search = s;
                searchText.onActionViewCollapsed();
                mPresenter.search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });
        mPresenter.viewIsReady();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTitle(String.valueOf(tab.getText()));
                mPresenter.tabSelected(String.valueOf(tab.getText()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_SEARCH, search);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        search = savedInstanceState.getString(KEY_SEARCH);
    }

    private void setupViewPager(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
            }
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mViewPagerAdapter = new MainPagerAdapter(this, fm);
        mViewPagerAdapter.addFragment(AlbumTabsFragment.getInstance(search), "Albums");
        mViewPagerAdapter.addFragment(ArtistTabsFragment.getInstance(search), "Artists");
        mViewPagerAdapter.addFragment(SongTabsFragment.getInstance(search), "Songs");
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mViewPagerAdapter);
        transaction.commit();
    }

    @Override
    public MainContract.Presenter supplyPresenter() {
        return new MainPresenter();
    }

    @Override
    public String getSearchText() {
        return search;
    }

    @Override
    public void render(String response) {
        MainTabsFragment fragment = mViewPagerAdapter
                .getItem(mViewPager.getCurrentItem());
        fragment.search(response);
    }

    @Override
    public boolean emptyRv() {
        return mViewPagerAdapter.getItem(mViewPager.getCurrentItem()).isRvEmpty();
    }

    @Override
    public void showLoading() {
        pb.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(ProgressBar.INVISIBLE);
    }
}
