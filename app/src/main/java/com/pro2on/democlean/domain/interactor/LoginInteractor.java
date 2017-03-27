package com.pro2on.democlean.domain.interactor;

import com.pro2on.democlean.domain.entity.User;
import com.pro2on.democlean.domain.model.SessionManager;
import com.pro2on.democlean.domain.repository.LoginRepository;
import com.pro2on.democlean.domain.repository.UserRepository;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class LoginInteractor {


    private final SessionManager sessionManager;
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;

    @Inject
    public LoginInteractor(SessionManager sessionManager, LoginRepository loginRepository, UserRepository userRepository) {
        this.sessionManager = sessionManager;
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
    }

    public Observable<Boolean> execute(final String login) {
        return  userRepository.getUser(login)
                .map(new Function<User, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull User user) throws Exception {

                        boolean result = false;
                        if (user != null) {
                            result = true;
                        }

                        return result;

                    }
                })
                .doOnNext(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean result) throws Exception {
                        if (result) {
                            loginRepository.put(login);
                        }
                    }
                });
    }

}
