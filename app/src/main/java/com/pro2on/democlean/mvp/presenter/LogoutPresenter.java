package com.pro2on.democlean.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.pro2on.democlean.domain.interactor.LogoutInteractor;
import com.pro2on.democlean.domain.model.SessionManager;
import com.pro2on.democlean.mvp.view.LogoutView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


@InjectViewState
public class LogoutPresenter extends BasePresenter<LogoutView> {


    private final LogoutInteractor logoutInteractor;


    public LogoutPresenter(LogoutInteractor logoutInteractor) {
        this.logoutInteractor = logoutInteractor;


        // start listen our model for command to exit from authorized zone
        unsubscribeOnDestroy(

                logoutInteractor
                        .getSessionState()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@NonNull Boolean isSessionStarted) throws Exception {

                                if (!isSessionStarted) {
                                    getViewState().onLogout();
                                }

                            }
                        })

        );

    }



    public void onLogout(){
        logoutInteractor.logout();
    }


}
