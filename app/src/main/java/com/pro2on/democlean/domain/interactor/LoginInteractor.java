package com.pro2on.democlean.domain.interactor;

import com.pro2on.democlean.domain.model.SessionManager;
import com.pro2on.democlean.domain.repository.LoginRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


public class LoginInteractor {


    private final SessionManager sessionManager;
    private final LoginRepository loginRepository;


    @Inject
    public LoginInteractor(SessionManager sessionManager, LoginRepository loginRepository) {
        this.sessionManager = sessionManager;
        this.loginRepository = loginRepository;
    }

    public Observable<Boolean> execute(final String login) {
        return Observable
                .create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> emmiter) throws Exception {


                        loginRepository.put(login);
                        sessionManager.startSession();


                        emmiter.onNext(sessionManager.isSessionStarted());
                        emmiter.onComplete();


                    }
                })
                .delay(1000, TimeUnit.MILLISECONDS);
    }

}
