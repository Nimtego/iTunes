package com.nimtego.plectrum.presentation.old.information_view.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.nimtego.plectrum.R
import com.nimtego.plectrum.presentation.base.BaseFragment
import com.nimtego.plectrum.presentation.old.information_view.song.model.SongDetailsModel
import com.nimtego.plectrum.presentation.utils.IpTagsK
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class SongInformationFragment : BaseFragment(), SongInformationView  {
    private var songImage: ImageView? = null
    private var information: TextView? = null

    @InjectPresenter
    internal lateinit var presenter: SongInformationPresenterImpl

    @ProvidePresenter
    fun provideRepositoryPresenter(): SongInformationPresenterImpl {
        return SongInformationPresenterImpl()
    }
companion object {
    fun newInstance(content: String): SongInformationFragment {
        val fragment = SongInformationFragment()
        val arguments = Bundle()
        arguments.putString(IpTagsK.SONG_ID.name, content)
        fragment.arguments = arguments
        return fragment
    }
}

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.song_information_fragment, container, false)
        information = view.findViewById(R.id.author)
        songImage = view.findViewById(R.id.song_image)

        presenter.viewReady(arguments.getString(IpTagsK.SONG_ID.name))
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /* this.mPresenter.viewReady(getArguments().getString(ALBUM_ID.name()));*/
    }

    override fun render(songDetailsModel: SongDetailsModel) {
        val sb = StringBuilder()
        sb.append(songDetailsModel.songName).append("\n\n")
                .append(songDetailsModel.songAlbumName).append("\n\n")
                .append(songDetailsModel.songArtistName).append("\n\n")
                .append(songDetailsModel.releaseDate).append("\n\n")
                .append(songDetailsModel.songPrice).append("\n\n")
        information!!.text = sb
        Picasso.get().load(songDetailsModel.songArtwork!!
                .replace("100x100", "400x400"))
                .into(songImage!!, object : Callback {
                    override fun onSuccess() {
                        /*      if (pb != null)
                            pb.setVisibility(View.GONE);*/
                    }

                    override fun onError(e: Exception) {
                        /*     if (pb != null)
                            pb.setVisibility(View.GONE);*/
                    }
                })
    }
}