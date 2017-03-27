package com.pro2on.democlean.domain.model;

import com.pro2on.democlean.domain.repository.LoginRepository;

import javax.inject.Inject;

/**
 * Date: 27.03.17
 * Time: 15:37
 * Created by pro2on in project DemoClean
 */

public class LogoutManager {

    private final LoginRepository loginRepository;


    @Inject
    public LogoutManager(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public void clearRepositories() {
        loginRepository.clear();
    }
}
