package com.igordanilchik.daggertest;

import javax.inject.Singleton;

import dagger.Component;

@SuppressWarnings("WeakerAccess")
@Singleton
@Component(modules = {AppModule.class, HttpClientModule.class})
public interface AppComponent {
    RepositoryComponent plus(RepositoryModule repositoryModule);
}
