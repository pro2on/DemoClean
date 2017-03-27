package com.pro2on.democlean.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.pro2on.democlean.application.DemoApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Date: 27.03.17
 * Time: 12:10
 * Created by pro2on in project DemoClean
 */
@Module
public class ApplicationModule {


    private DemoApp application;


    public ApplicationModule(DemoApp application) {
        this.application = application;
    }



    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }



    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


}



