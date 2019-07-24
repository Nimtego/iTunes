package com.nimtego.plectrum.presentation.old.main;

import android.content.Context;

import com.nimtego.plectrum.data.entity.mapper.EntityDataMapper;
import com.nimtego.plectrum.domain.interactor.MainViewInteractor;
import com.nimtego.plectrum.presentation.ui.activity.AppActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    //private MainPresenter presenter;

    @Mock
    private Context mockContext;
    @Mock
    private AppActivity mockMainView;
    @Mock
    private MainViewInteractor mockInteracter;
    @Mock
    private EntityDataMapper mockModelDataMapper;

    @Before
    public void setUp() {
        //presenter = new MainPresenter();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUserListPresenterInitialize() {
//        given(mockMainView.context()).willReturn(mockContext);
//        when(mockMainView.getSearchText()).thenReturn("metallica");

       // presenter.viewIsReady();
//        verify(mockMainView).showLoading();
        verify(mockInteracter).execute(any(DisposableObserver.class),
                any(MainViewInteractor.Params.class));
    }

    @Test
    public void searchTest() {
        //presenter.search("Test");
    }

    @Test
    public void tabSelected() {

    }

    @Test
    public void pushInRV() {
    }

    @Test
    public void longPushInRV() {
    }

    @Test
    public void viewIsReady() {
    }
}