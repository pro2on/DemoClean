package com.pro2on.democlean.data.repository;

import com.pro2on.democlean.data.entity.UserEntity;
import com.pro2on.democlean.data.entity.mapper.UserEntityDataMapper;
import com.pro2on.democlean.data.repository.datastore.UserDataStore;
import com.pro2on.democlean.data.repository.datastore.UserDataStoreFactory;
import com.pro2on.democlean.domain.entity.User;
import com.pro2on.democlean.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Date: 27.03.17
 * Time: 19:41
 * Created by pro2on in project DemoClean
 */
@Singleton
public class UserDataRepository implements UserRepository {


    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    @Inject
    public UserDataRepository(UserDataStoreFactory userDataStoreFactory, UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = userDataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }



    @Override
    public Observable<User> getUser(String login) {

        final UserDataStore userDataStore = userDataStoreFactory.create(login);
        return userDataStore.get(login).map(new Function<UserEntity, User>() {
            @Override
            public User apply(@NonNull UserEntity userEntity) throws Exception {
                return userEntityDataMapper.transform(userEntity);
            }
        });


    }
}
