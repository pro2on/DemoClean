package com.pro2on.democlean.domain.interactor;

import com.pro2on.democlean.domain.model.SessionManager;
import com.pro2on.democlean.domain.repository.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 27.03.17
 * Time: 16:26
 * Created by pro2on in project DemoClean
 */

public class LogoutInteractor {


    private final SessionManager sessionManager;
    private final LoginRepository loginRepository;


    @Inject
    public LogoutInteractor(SessionManager sessionManager, LoginRepository loginRepository) {
        this.sessionManager = sessionManager;
        this.loginRepository = loginRepository;
    }


    public Observable<Boolean> getSessionState() {
        return sessionManager.getSessionStartedObservable();
    }


    public void logout() {

        // clear repository
        loginRepository.clear();

        // stop session
        sessionManager.stopSession();

    }

}
