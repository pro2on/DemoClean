package com.pro2on.democlean.domain.interactor;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.pro2on.democlean.data.entity.UserEntity;
import com.pro2on.democlean.domain.entity.User;
import com.pro2on.democlean.domain.exception.UserNotFoundException;
import com.pro2on.democlean.domain.repository.LoginRepository;
import com.pro2on.democlean.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Date: 27.03.17
 * Time: 20:13
 * Created by pro2on in project DemoClean
 */

public class UserInteractor {

    private final LogoutInteractor logoutInteractor;
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;


    @Inject
    public UserInteractor(LogoutInteractor logoutInteractor, LoginRepository loginRepository, UserRepository userRepository) {
        this.logoutInteractor = logoutInteractor;
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
    }

    public Observable<User> getUser() {


        return loginRepository.get()
                .flatMap(new Function<String, ObservableSource<User>>() {
                    @Override
                    public ObservableSource<User> apply(@NonNull String login) throws Exception {
                        return userRepository.getUser(login);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {

                        if (throwable instanceof HttpException) { // for example, if user remove his account

                            if (((HttpException) throwable).code() == 404) {

                                logoutInteractor.logout();

                            }

                        } else if (throwable instanceof UserNotFoundException) { // some problem with cache

                            logoutInteractor.logout();
                        }
                    }
                });


    }


}
