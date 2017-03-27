package com.pro2on.democlean.domain.model;

import com.jakewharton.rxrelay2.BehaviorRelay;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;



@Singleton
public class SessionManager {


    // this is our core session watcher
    private BehaviorRelay<Boolean> isSessionStartedRelay = BehaviorRelay.createDefault(false);



    @Inject
    public SessionManager() {
    }



    // for our model from presentation layer
    public Observable<Boolean> getSessionStartedObservable() {
        return isSessionStartedRelay;
    }



    public boolean isSessionStarted() {
        return isSessionStartedRelay.getValue();
    }


    public void startSession() {
        isSessionStartedRelay.accept(true);
    }


    public void stopSession() {
        isSessionStartedRelay.accept(false);
    }

}
