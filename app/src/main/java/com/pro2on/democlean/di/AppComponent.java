package com.pro2on.democlean.di;

import com.pro2on.democlean.di.module.ApplicationModule;
import com.pro2on.democlean.di.module.DataModule;
import com.pro2on.democlean.ui.activity.LoginActivity;
import com.pro2on.democlean.ui.activity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Date: 27.03.17
 * Time: 12:10
 * Created by pro2on in project DemoClean
 */
@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class})
public interface AppComponent {


    void inject(SplashActivity activity);
    void inject(LoginActivity activity);

}
