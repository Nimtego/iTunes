package com.nimtego.itunes.presentation.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.nimtego.itunes.App;
import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseView;
import com.nimtego.itunes.presentation.main.adapter.MainPagerAdapter;
import com.nimtego.itunes.presentation.main.albums.AlbumTabsFragment;
import com.nimtego.itunes.presentation.main.artists.ArtistTabsFragment;
import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.songs.SongTabsFragment;
import com.nimtego.itunes.presentation.utils.TabSelectedListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseView<MainContract.Presenter>
        implements MainContract.View<MainContract.Presenter> {

    final static String KEY_SEARCH = "key_search";

    @BindView(R.id.search_edit_text) SearchView searchText;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private MainPagerAdapter mViewPagerAdapter;
    private ProgressBar pb;
    private String search = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            search = savedInstanceState.getString(KEY_SEARCH);
        }

        Toolbar mToolBar = findViewById(R.id.tool_bar);
        setSupportActionBar(mToolBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(savedInstanceState);

        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        pb = findViewById(R.id.progress_bar);
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
        mTabLayout.addOnTabSelectedListener((TabSelectedListener) tab -> {
            setTitle(String.valueOf(tab.getText()));
            mPresenter.tabSelected(String.valueOf(tab.getText()));
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
        if (savedInstanceState != null) {
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
        return App.getComponent().mainPresenter();
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
