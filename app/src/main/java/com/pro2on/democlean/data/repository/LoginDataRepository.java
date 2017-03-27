package com.pro2on.democlean.data.repository;

import com.pro2on.democlean.data.cache.LoginCache;
import com.pro2on.democlean.domain.repository.LoginRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class LoginDataRepository implements LoginRepository {


    private final LoginCache loginCache;


    @Inject
    public LoginDataRepository(LoginCache loginCache) {
        this.loginCache = loginCache;
    }


    @Override
    public Observable<String> get() {
        return loginCache.get();
    }

    @Override
    public void put(String login) {
        loginCache.put(login);
    }

    @Override
    public void clear() {
        loginCache.clear();
    }

    @Override
    public boolean isCached() {
        return loginCache.isCached();
    }

}
