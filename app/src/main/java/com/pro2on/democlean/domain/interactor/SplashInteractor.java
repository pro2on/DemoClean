package com.pro2on.democlean.domain.interactor;

import com.pro2on.democlean.domain.model.SessionManager;
import com.pro2on.democlean.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Date: 27.03.17
 * Time: 14:46
 * Created by pro2on in project DemoClean
 */

public class SplashInteractor {


    private final LoginRepository loginRepository;
    private final SessionManager sessionManager;


    @Inject
    public SplashInteractor(LoginRepository loginRepository, SessionManager sessionManager) {
        this.loginRepository = loginRepository;
        this.sessionManager = sessionManager;
    }



    public Observable<Boolean> isSessionStartedOrStartIfPossible() {
        return sessionManager
                .getSessionStartedObservable()
                .map(new Function<Boolean, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Boolean isStarted) throws Exception {

                        if (!isStarted) {
                            isStarted = loginRepository.isCached();
                        }

                        return isStarted;
                    }
                })
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        sessionManager.startSession();
                    }
                });

    }

}
