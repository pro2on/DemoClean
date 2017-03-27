package com.pro2on.democlean.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.pro2on.democlean.domain.entity.User;
import com.pro2on.democlean.domain.interactor.UserInteractor;
import com.pro2on.democlean.mvp.view.UserView;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Date: 27.03.17
 * Time: 20:22
 * Created by pro2on in project DemoClean
 */
@InjectViewState
public class UserPresenter extends BasePresenter<UserView> {


    private final UserInteractor interactor;

    private boolean isLoading = false;



    public UserPresenter(UserInteractor interactor) {
        this.interactor = interactor;
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadUserInfo();
    }


    private void loadUserInfo() {


        if (isLoading) {
            return;
        }


        isLoading = true;
        getViewState().hideError();
        getViewState().showProgress();


        unsubscribeOnDestroy(

                interactor
                        .getUser()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<User>() {
                            @Override
                            public void accept(@NonNull User user) throws Exception {

                                isLoading = false;
                                getViewState().hideProgress();

                                if (user != null) {

                                    getViewState().showUserName(user.getLogin());

                                    if (user.getProjects() != null) {
                                        getViewState().setupRepositories(user.getProjects());
                                    }
                                }

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                getViewState().hideProgress();
                                getViewState().showError(throwable.getMessage());
                            }
                        })

        );

    }



    public void closeError() {
        getViewState().hideError();
    }


}
