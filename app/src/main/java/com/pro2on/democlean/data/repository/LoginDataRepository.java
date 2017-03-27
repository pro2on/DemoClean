package com.pro2on.democlean.data.repository;

import com.pro2on.democlean.domain.repository.LoginRepository;

import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Date: 27.03.17
 * Time: 14:56
 * Created by pro2on in project DemoClean
 */
@Singleton
public class LoginDataRepository implements LoginRepository {





    @Override
    public Observable<String> get() {
        return null;
    }

    @Override
    public void put(String login) {

    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isCached() {
        return false;
    }
}
