package com.pro2on.democlean.di.module;

import com.pro2on.democlean.data.cache.LoginCache;
import com.pro2on.democlean.data.cache.LoginCacheImpl;
import com.pro2on.democlean.data.cache.UserCache;
import com.pro2on.democlean.data.cache.UserCacheImpl;
import com.pro2on.democlean.data.repository.LoginDataRepository;
import com.pro2on.democlean.data.repository.UserDataRepository;
import com.pro2on.democlean.domain.repository.LoginRepository;
import com.pro2on.democlean.domain.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * For injecting objects from data layer
 */
@Module
public class DataModule {


    @Provides
    @Singleton
    public LoginCache provideLoginCache(LoginCacheImpl loginCache) {
        return loginCache;
    }


    @Provides
    @Singleton
    public LoginRepository provideLoginRepository(LoginDataRepository loginDataRepository) {
        return loginDataRepository;
    }


    @Provides @Singleton
    public UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }


    @Provides
    @Singleton
    public UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }

}
