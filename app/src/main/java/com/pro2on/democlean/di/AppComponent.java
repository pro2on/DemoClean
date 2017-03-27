package com.pro2on.democlean.di;

import com.pro2on.democlean.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Date: 27.03.17
 * Time: 12:10
 * Created by pro2on in project DemoClean
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface AppComponent {
}
