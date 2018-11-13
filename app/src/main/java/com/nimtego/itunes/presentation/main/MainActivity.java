package com.nimtego.itunes.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nimtego.itunes.R;
import com.nimtego.itunes.presentation.base.BaseView;
import com.nimtego.itunes.presentation.main.adapter.ViewPagerAdapter;
import com.nimtego.itunes.presentation.main.fragments.AlbumTabsFragment;
import com.nimtego.itunes.presentation.main.fragments.ArtistTabsFragment;
import com.nimtego.itunes.presentation.main.fragments.MainTabsContract;
import com.nimtego.itunes.presentation.main.fragments.MainTabsFragment;
import com.nimtego.itunes.presentation.main.fragments.SongTabsFragment;
import com.nimtego.itunes.presentation.main.model.MainDataModel;


public class MainActivity extends BaseView<MainContract.Presenter>
        implements MainContract.View<MainContract.Presenter> {

    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private EditText searchText;
    private ViewPagerAdapter mViewPagerAdapter;
    private ProgressBar pb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.search_edit_text);
        pb = findViewById(R.id.progressBar);
        searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    searchText.setCursorVisible(true);
                } else {
                    searchText.setCursorVisible(false);
                }
            }
        });
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mPresenter.search();
                    searchText.setCursorVisible(false);
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
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    @Override
    public MainContract.Presenter supplyPresenter() {
        return new MainPresenter();
    }

    @Override
    public String getSearchText() {
        return String.valueOf(searchText.getText());
    }

    @Override
    public void render(String response) {
        MainTabsFragment fragment = mViewPagerAdapter
                        .getItem(mViewPager.getCurrentItem());
        fragment.search(response);
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
