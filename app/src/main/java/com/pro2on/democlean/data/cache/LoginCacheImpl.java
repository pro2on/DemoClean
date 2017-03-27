package com.pro2on.democlean.data.cache;

import android.content.SharedPreferences;
import android.text.TextUtils;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


@Singleton
public class LoginCacheImpl implements LoginCache {



    private static final String KEY_LOGIN = "user_login";


    private final SharedPreferences sharedPreferences;


    @Inject
    public LoginCacheImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<String> get() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emmiter) throws Exception {

                final String login = sharedPreferences.getString(KEY_LOGIN, "");

                emmiter.onNext(login);
                emmiter.onComplete();


            }
        });
    }

    @Override
    public void put(String login) {
        sharedPreferences.edit().putString(KEY_LOGIN, login).apply();
    }

    @Override
    public void clear() {
        sharedPreferences.edit().remove(KEY_LOGIN).apply();
    }

    @Override
    public boolean isCached() {
        final String login = sharedPreferences.getString(KEY_LOGIN, "");
        return !TextUtils.isEmpty(login);
    }
}
