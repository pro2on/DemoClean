package com.pro2on.democlean.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.pro2on.democlean.R;
import com.pro2on.democlean.application.DemoApp;
import com.pro2on.democlean.domain.interactor.LoginInteractor;
import com.pro2on.democlean.mvp.presenter.LoginPresenter;
import com.pro2on.democlean.mvp.view.LoginView;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginActivity extends MvpAppCompatActivity implements LoginView, DialogInterface.OnCancelListener {


    private static final int LAYOUT = R.layout.activity_login;


    @BindView(R.id.etUsername) EditText loginEditView;
    @BindView(R.id.btnStartSession) Button loginButton;
    @BindView(R.id.pbLoading) ProgressBar progressView;



    private AlertDialog errorDialog;


    @Inject
    LoginInteractor loginInteractor;


    @InjectPresenter
    LoginPresenter presenter;


    @ProvidePresenter LoginPresenter provideLoginPresenter() {
        return new LoginPresenter(loginInteractor);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((DemoApp)getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT);
        ButterKnife.bind(this);

        errorDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setOnCancelListener(this)
                .create();
    }



    @Override
    public void showProgress() {
        toggleProgressVisibility(true);
    }

    @Override
    public void hideProgress() {
        toggleProgressVisibility(false);
    }

    @Override
    public void showError(int message) {
        showError(getString(message));
    }

    @Override
    public void showError(String message) {
        errorDialog.setMessage(message);
        errorDialog.show();
    }

    @Override
    public void hideError() {
        errorDialog.cancel();
    }

    @Override
    public void showFieldError(Integer loginError) {
        if (loginError != null) {
            loginEditView.setError(getString(loginError));
        }
    }

    @Override
    public void successLogin() {
        final Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }

    private void toggleProgressVisibility(final boolean show) {
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        loginButton.setVisibility(show ? View.GONE : View.VISIBLE);

        // also need enable/disable editText
        loginEditView.setEnabled(!show);
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        presenter.errorCancel();
    }


    @Override
    protected void onDestroy() {
        if (errorDialog != null) {
            errorDialog.setOnCancelListener(null);
            errorDialog.dismiss();
        }

        super.onDestroy();
    }


    @OnClick(R.id.btnStartSession)
    public void attemptLogin() {
        presenter.login(loginEditView.getText().toString());
    }

}
