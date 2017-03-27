package com.pro2on.democlean.data.entity.mapper;

import com.pro2on.democlean.data.entity.UserEntity;
import com.pro2on.democlean.domain.entity.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Date: 27.03.17
 * Time: 19:21
 * Created by pro2on in project DemoClean
 */
@Singleton
public class UserEntityDataMapper {

    @Inject
    public UserEntityDataMapper() {
    }


    public User transform(UserEntity entity) {
        User user = null;
        if (entity != null) {
            user = new User();
            user.setId(entity.getId());
            user.setLogin(entity.getLogin());
            user.setEmail(entity.getEmail());
            user.setUrl(entity.getUrl());
            user.setProjects(entity.getProjects());
        }
        return user;
    }
}
