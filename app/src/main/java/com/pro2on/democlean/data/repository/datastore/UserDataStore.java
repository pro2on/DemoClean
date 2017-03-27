package com.pro2on.democlean.data.repository.datastore;

import com.pro2on.democlean.data.entity.UserEntity;

import io.reactivex.Observable;

/**
 * Date: 27.03.17
 * Time: 19:41
 * Created by pro2on in project DemoClean
 */

public interface UserDataStore {


    Observable<UserEntity> get(String login);

}
