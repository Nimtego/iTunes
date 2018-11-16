package com.nimtego.itunes.presentation.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseView;
import com.nimtego.itunes.presentation.main.adapter.ViewPagerAdapter;
import com.nimtego.itunes.presentation.main.albums.AlbumTabsFragment;
import com.nimtego.itunes.presentation.main.artists.ArtistTabsFragment;
import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.songs.SongTabsFragment;

import java.lang.reflect.Field;


public class MainActivity extends BaseView<MainContract.Presenter>
        implements MainContract.View<MainContract.Presenter> {

    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SearchView searchText;
    private ViewPagerAdapter mViewPagerAdapter;
    private ProgressBar pb;
    private String search = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_edit_text);
/*        searchText.findViewById(android.support.v7.appcompat.R.id.search_src_text)
                .setBackgroundResource(R.drawable.rounded_edittext);*/
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

        mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.viewpager);
        setupViewPager();

        mTabLayout = findViewById(R.id.tablayout);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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

    private void setupViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new AlbumTabsFragment(), "Albums");
        mViewPagerAdapter.addFragment(new ArtistTabsFragment(), "Artists");
        mViewPagerAdapter.addFragment(new SongTabsFragment(), "Songs");
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mViewPagerAdapter);
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
