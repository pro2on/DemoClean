package com.pro2on.democlean.domain.repository;

import com.pro2on.democlean.domain.entity.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Date: 27.03.17
 * Time: 16:06
 * Created by pro2on in project DemoClean
 */

public interface UserRepository {


    public Observable<User> getUser(String login);


}
