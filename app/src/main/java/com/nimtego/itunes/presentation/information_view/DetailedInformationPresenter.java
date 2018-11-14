package com.nimtego.itunes.presentation.information_view;

import com.nimtego.itunes.data.rest.pojo.SongsRepository;
import com.nimtego.itunes.domain.interactor.SongIntTmp;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class DetailedInformationPresenter
        extends BasePresenter<DetailedInformationContract.View,
        BaseContract.Interactor>
        implements DetailedInformationContract.Presenter<DetailedInformationContract.View,
        BaseContract.Interactor> {

    private String id;
    private SongsRepository mSongResult;
    private List<String> songsList;

    public DetailedInformationPresenter(BaseContract.Interactor interactor) {
        super(interactor);
    }

    public DetailedInformationPresenter() {
        this(new SongIntTmp());
    }
}
