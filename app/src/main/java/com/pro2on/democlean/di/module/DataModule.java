package com.pro2on.democlean.di.module;

import com.pro2on.democlean.data.cache.LoginCache;
import com.pro2on.democlean.data.cache.LoginCacheImpl;
import com.pro2on.democlean.data.repository.LoginDataRepository;
import com.pro2on.democlean.domain.repository.LoginRepository;

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


}
