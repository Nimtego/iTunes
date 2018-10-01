package com.nimtego.itunes;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTabLayout = findViewById(R.id.tablayout);
        mToolBar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewpager);
/*        getActivity().setSupportActionBar(mToolBar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.add(new MyFragment(), "First fragment"));
        adapter.add(new MyFragment(), "Second fragment"));*/
    }
}
