package com.pro2on.democlean.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.pro2on.democlean.domain.interactor.UserInteractor;
import com.pro2on.democlean.mvp.view.UserView;

/**
 * Date: 27.03.17
 * Time: 20:22
 * Created by pro2on in project DemoClean
 */
@InjectViewState
public class UserPresenter extends BasePresenter<UserView> {


    private final UserInteractor interactor;


    public UserPresenter(UserInteractor interactor) {
        this.interactor = interactor;
    }



}
