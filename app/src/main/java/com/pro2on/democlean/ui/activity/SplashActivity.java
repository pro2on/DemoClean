package com.pro2on.democlean.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.pro2on.democlean.application.DemoApp;
import com.pro2on.democlean.domain.interactor.SplashInteractor;
import com.pro2on.democlean.mvp.presenter.SplashPresenter;
import com.pro2on.democlean.mvp.view.SplashView;


import javax.inject.Inject;

import timber.log.Timber;



public class SplashActivity extends MvpAppCompatActivity implements SplashView {


    @Inject
    SplashInteractor splashInteractor;

    @InjectPresenter
    SplashPresenter presenter;

    @ProvidePresenter public SplashPresenter provideSplashPresenter() {
        return new SplashPresenter(splashInteractor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((DemoApp)getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);


        // By default view attaches to presenter in onStart() method,
        // but we attach it manually for earlier check authorization state.
        getMvpDelegate().onAttach();


        presenter.checkIsUserAuthorized();

    }

    @Override
    public void setAuthorized(boolean isAuthorized) {
        Timber.d("user is authorized: %s", isAuthorized);
        startActivityForResult(new Intent(this, isAuthorized ? MainActivity.class : LoginActivity.class) , 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }

}