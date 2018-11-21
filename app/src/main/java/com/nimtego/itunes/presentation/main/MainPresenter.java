package com.nimtego.itunes.presentation.main;

import com.nimtego.itunes.domain.interactor.MainViewInteractor;
import com.nimtego.itunes.presentation.base.BaseContract;
import com.nimtego.itunes.presentation.base.BasePresenter;
import com.nimtego.itunes.presentation.main.model.MainDataModel;

import javax.inject.Inject;

public class MainPresenter
        extends BasePresenter<MainContract.View,
        BaseContract.Interactor<MainDataModel,
                MainViewInteractor.Params>>
        implements MainContract.Presenter<MainContract.View,
        BaseContract.Interactor<MainDataModel,
                MainViewInteractor.Params>> {

    private final String TAG = this.getClass().getCanonicalName();

    @Inject
    public MainPresenter(BaseContract.Interactor<MainDataModel,
            MainViewInteractor.Params> interactor) {
        super(interactor);
    }


    public MainPresenter() {
        this(null);
        // TODO: 29.10.2018 replaceable di
    }

    @Override
    public void search() {
        view.render(view.getSearchText());
    }

    @Override
    public void tabSelected(String tabName) {
        search();
    }

    @Override
    public void viewIsReady() {
        if (!view.getSearchText().isEmpty())
            search();
    }

}
