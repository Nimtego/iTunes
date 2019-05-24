package com.nimtego.plectrum.presentation.information_view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.information_view.album.AlbumInformationFragment
import com.nimtego.plectrum.presentation.information_view.album.AlbumInformationFragmentK
import com.nimtego.plectrum.presentation.information_view.artist.ArtistInformationFragment
import com.nimtego.plectrum.presentation.information_view.artist.ArtistInformationFragmentK
import com.nimtego.plectrum.presentation.information_view.song.SongInformationFragment
import com.nimtego.plectrum.presentation.information_view.song.SongInformationFragmentK
import com.nimtego.plectrum.presentation.utils.FragmentType
import com.nimtego.plectrum.presentation.utils.FragmentTypeK
import com.nimtego.plectrum.presentation.utils.IpTagsK

class DetailedInformationActivityK : MvpAppCompatActivity(), DetailedInformationView {
    private val RESPONSE_NAME = "Name"

    fun getCallingIntent(context: Context, name: String): Intent {
        val callingIntent = Intent(context, DetailedInformationActivityK::class.java)
        callingIntent.putExtra(RESPONSE_NAME, name)
        return callingIntent
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        var fragment: Fragment? = null
        if (intent.extras != null) {
            val type = intent.extras!!.getString(FragmentTypeK.TYPE.name)!!
            if (type == FragmentTypeK.ALBUM.name)
                fragment = AlbumInformationFragmentK
                        .newInstance(intent.extras!!.getString(IpTagsK.ALBUM_ID.name)) as Fragment
            if (type == FragmentTypeK.SONG.name)
                fragment = SongInformationFragmentK
                        .newInstance(intent.extras!!.getString(IpTagsK.SONG_ID.name)) as Fragment
            if (type == FragmentTypeK.ARTIST.name)
                fragment = ArtistInformationFragmentK
                        .newInstance(intent.extras!!.getString(IpTagsK.ARTIST_ID.name)) as Fragment
        }
        if (savedInstanceState == null && fragment != null)
            supportFragmentManager.beginTransaction()
                    .add(R.id.frg, fragment).commit()
    }

    override fun onStart() {
        super.onStart()
    }
}