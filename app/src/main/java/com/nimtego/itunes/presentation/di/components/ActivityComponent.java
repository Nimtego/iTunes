package com.nimtego.itunes.presentation.di.components;

import android.app.Activity;

import com.nimtego.itunes.presentation.di.modules.ActivityModule;

import dagger.Component;


@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
