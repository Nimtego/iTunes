package com.nimtego.plectrum.presentation.ui.fragment

import android.os.Bundle

class MovieTabFragment {

    companion object {
        fun getInstance(): TabContentFragment {
            val fragment = TabContentFragment()

            val arguments = Bundle()
            arguments.putString(TAB_NAME, TAB)
            fragment.arguments = arguments

            return fragment
        }

        val TAB_NAME = "TAB_NAME"
        val TAB = "MOVIE_TAB"
    }
}