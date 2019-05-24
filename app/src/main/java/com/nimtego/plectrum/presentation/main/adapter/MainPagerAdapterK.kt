package com.nimtego.plectrum.presentation.main.adapter

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nimtego.plectrum.presentation.main.fragments.MainTabsFragmentK
import com.nimtego.plectrum.presentation.mvp.MainView
import java.util.*

class MainPagerAdapterK(private val parent: MainView, manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val mFragmentList: MutableList<MainTabsFragmentK>
    private val mFragmentTitleList: MutableList<String>

    init {
        mFragmentList = ArrayList()
        mFragmentTitleList = ArrayList()
    }

    override fun getItem(position: Int): MainTabsFragmentK {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: MainTabsFragmentK, title: String) {
        fragment.onAttach(parent as Context)
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList[position]
    }

}