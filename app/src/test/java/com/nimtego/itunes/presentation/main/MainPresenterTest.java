package com.nimtego.itunes.presentation.main;

import android.content.Context;

import com.nimtego.itunes.domain.interactor.MainViewInteractor;
import com.nimtego.itunes.presentation.mapper.AlbumModelDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    private MainPresenter presenter;

    @Mock
    private Context mockContext;
    @Mock
    private MainActivity mockMainView;
    @Mock
    private MainViewInteractor mockInteracter;
    @Mock
    private AlbumModelDataMapper mockModelDataMapper;

    @Before
    public void setUp() {
        presenter = new MainPresenter(mockInteracter, mockModelDataMapper);
        presenter.attach(mockMainView);
    }
    @Test
    @SuppressWarnings("unchecked")
    public void testUserListPresenterInitialize() {
        given(mockMainView.context()).willReturn(mockContext);
        when(mockMainView.getSearchText()).thenReturn("metallica");

        presenter.viewIsReady();
        verify(mockMainView).showLoading();
        verify(mockInteracter).execute(any(DisposableObserver.class),
                any(MainViewInteractor.Params.class));
    }
    @Test
    public void searchTest() {
        presenter.search();
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