package com.pro2on.democlean.domain.interactor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Date: 27.03.17
 * Time: 15:53
 * Created by pro2on in project DemoClean
 */

public class LoginInteractor {


    @Inject
    public LoginInteractor() {
    }


    public Observable<Boolean> execute(String login) {
        return Observable.just(false);
    }

}
