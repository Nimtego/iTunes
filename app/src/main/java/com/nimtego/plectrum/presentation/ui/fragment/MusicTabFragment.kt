package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle

class MusicTabFragment {

    companion object {
        fun getInstance(): TabContentFragment {
            val fragment = TabContentFragment()

            val arguments = Bundle()
//            arguments.putString(TAB_NAME, tabName)
//            fragment.arguments = arguments

            return fragment
        }

        val TAB_NAME = "TAB_NAME"
    }
}