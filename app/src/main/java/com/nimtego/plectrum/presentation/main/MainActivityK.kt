package com.nimtego.plectrum.presentation.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.main.adapter.MainPagerAdapterK
import com.nimtego.plectrum.presentation.main.albums.AlbumTabsFragment
import com.nimtego.plectrum.presentation.main.albums.AlbumTabsFragmentK
import com.nimtego.plectrum.presentation.main.artists.ArtistTabsFragment
import com.nimtego.plectrum.presentation.main.artists.ArtistTabsFragmentK
import com.nimtego.plectrum.presentation.main.songs.SongTabsFragment
import com.nimtego.plectrum.presentation.mvp.MainView
import com.nimtego.plectrum.presentation.utils.TabSelectedListener
import java.util.*


class MainActivityK : MvpAppCompatActivity(), MainView {

    @BindView(R.id.search_edit_text)
    internal var searchText: SearchView? = null
    private var mViewPager: ViewPager? = null
    private var mTabLayout: TabLayout? = null
    private var mViewPagerAdapter: MainPagerAdapterK? = null
    private var pb: ProgressBar? = null
    private var search: String? = ""

    @InjectPresenter
    internal lateinit var presenter: MainPresenterK

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        if (savedInstanceState != null) {
            search = savedInstanceState.getString(KEY_SEARCH)
        }

        val mToolBar = findViewById<Toolbar>(R.id.tool_bar)
        setSupportActionBar(mToolBar)

        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayHomeAsUpEnabled(true)

        mViewPager = findViewById(R.id.view_pager)
        setupViewPager(savedInstanceState)

        mTabLayout = findViewById(R.id.tab_layout)
        mTabLayout!!.setupWithViewPager(mViewPager)

        pb = findViewById(R.id.progress_bar)
        searchText!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                search = s
                searchText!!.onActionViewCollapsed()
                presenter.search()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }

        })
        presenter.viewIsReady()
    }

    override fun onStart() {
        super.onStart()
        mTabLayout?.addOnTabSelectedListener(TabSelectedListener { tab ->
            setTitle(tab.getText().toString())
            presenter.tabSelected(tab.getText().toString())
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_SEARCH, search)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        search = savedInstanceState.getString(KEY_SEARCH)
    }

    private fun setupViewPager(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            for (fragment in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        mViewPagerAdapter = MainPagerAdapterK(this, fm)
        mViewPagerAdapter!!.addFragment(AlbumTabsFragmentK.getInstance(search), "Albums")
        mViewPagerAdapter!!.addFragment(ArtistTabsFragmentK.getInstance(search), "Artists")
        mViewPagerAdapter!!.addFragment(SongTabsFragmentK.getInstance(search), "Songs")
        mViewPager!!.offscreenPageLimit = 3
        mViewPager!!.adapter = mViewPagerAdapter
        transaction.commit()
    }

//    override fun supplyPresenter(): MainContract.Presenter<*, *> {
//        return App.getComponent().mainPresenter()
//    }
//
//    override fun getSearchText(): String? {
//        return search
//    }

//Mark: view methods

    override fun showProgress() {
        pb?.visibility = ProgressBar.VISIBLE
    }

    override fun hideProgress() {
        pb?.visibility = ProgressBar.INVISIBLE
    }

    override fun render(response: String) {
        val fragment = mViewPagerAdapter!!
                .getItem(mViewPager!!.currentItem)
        fragment.search(response)
    }

    override fun emptyRv(): Boolean {
        return mViewPagerAdapter!!.getItem(mViewPager!!.currentItem).isRvEmpty
    }

//    override fun showLoading() {
//        pb!!.visibility = ProgressBar.VISIBLE
//    }
//
//    override fun hideLoading() {
//        pb!!.visibility = ProgressBar.INVISIBLE
//    }

    companion object {

        internal val KEY_SEARCH = "key_search"
    }
}