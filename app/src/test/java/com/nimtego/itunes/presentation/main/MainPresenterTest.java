package com.nimtego.itunes.presentation.main;

import android.content.Context;

import com.nimtego.itunes.domain.interactor.MainViewInteractor;
import com.nimtego.itunes.presentation.mapper.AlbumModelDataMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {
    private MainPresenter presenter;

    @Mock
    private Context mockContext;
    @Mock
    private MainActivity mockMainActivity;
    @Mock
    private MainViewInteractor mockInteracter;
    @Mock
    private AlbumModelDataMapper mockModelDataMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void search() {
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