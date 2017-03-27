package com.pro2on.democlean.application;

import android.app.Application;

import com.arellomobile.mvp.MvpFacade;
import com.pro2on.democlean.BuildConfig;
import com.pro2on.democlean.application.timber.CrashReportingTree;
import com.pro2on.democlean.di.AppComponent;
import com.pro2on.democlean.di.DaggerAppComponent;
import com.pro2on.democlean.di.module.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Date: 27.03.17
 * Time: 11:52
 * Created by pro2on in project DemoClean
 */

public class DemoApp extends Application {


    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);



        // Plant Timber Tree
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }



        // init storage for moxy presenters
        MvpFacade.init();


        // resolve dagger 2 dependencies
        resolveDependencies();


    }


    public AppComponent getAppComponent() {
        return appComponent;
    }


    private void resolveDependencies() {

        appComponent =
                DaggerAppComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .build();

    }
}
