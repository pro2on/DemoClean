package com.pro2on.democlean.data.repository.datastore;

import com.pro2on.democlean.data.cache.UserCache;
import com.pro2on.democlean.data.entity.UserEntity;

import io.reactivex.Observable;

/**
 * Date: 27.03.17
 * Time: 19:43
 * Created by pro2on in project DemoClean
 */

public class DiskUserDataStore implements UserDataStore {

    private final UserCache userCache;


    public DiskUserDataStore(UserCache userCache) {
        this.userCache = userCache;
    }

    @Override
    public Observable<UserEntity> get(String login) {
        return userCache.get(login);
    }

}
