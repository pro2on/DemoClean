package com.pro2on.democlean.domain.interactor;

import com.pro2on.democlean.domain.model.LogoutManager;
import com.pro2on.democlean.domain.model.SessionManager;
import com.pro2on.democlean.domain.repository.LoginRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


public class SplashInteractor {


    private final LoginRepository loginRepository;
    private final SessionManager sessionManager;
    private final LogoutManager logoutManager;


    public SplashInteractor(LoginRepository loginRepository, SessionManager sessionManager, LogoutManager logoutManager) {
        this.loginRepository = loginRepository;
        this.sessionManager = sessionManager;
        this.logoutManager = logoutManager;
    }

    public Observable<Boolean> isSessionIsStartedOrStartSessionIfPossible() {
        return Observable
                .create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {


                        emitter.onNext(sessionManager.isSessionStarted() || loginRepository.isCached());
                        emitter.onComplete();


                    }
                })
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        sessionManager.startSession();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        logoutManager.clearRepositories();
                    }
                });

    }

}
