package com.pro2on.democlean.mvp.presenter;

import android.text.TextUtils;

import com.arellomobile.mvp.InjectViewState;
import com.pro2on.democlean.R;
import com.pro2on.democlean.domain.interactor.LoginInteractor;
import com.pro2on.democlean.mvp.view.LoginView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {



    private final LoginInteractor loginInteractor;


    public LoginPresenter(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }



    public void login(String login) {

        Integer loginError = null;

        getViewState().showFieldError(null);


        if (TextUtils.isEmpty(login)) {
            loginError = R.string.error_field_required;
        }

        if (loginError != null) {
            getViewState().showFieldError(loginError);
            return;
        }


//        login = "aasdfszdfsdfsd12";

        getViewState().showProgress();

        unsubscribeOnDestroy(

                loginInteractor.execute(login)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean result) throws Exception {

                                getViewState().hideProgress();

                                if (result) {
                                    getViewState().successLogin();
                                } else {
                                    getViewState().showError(R.string.error_login);
                                }



                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {

                                Timber.e(throwable);

                                getViewState().hideProgress();
                                getViewState().showError(throwable.getMessage());

                            }
                        })

        );

    }


    public void errorCancel() {
        getViewState().hideError();
    }
}