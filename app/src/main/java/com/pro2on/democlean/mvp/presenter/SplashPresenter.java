package com.pro2on.democlean.mvp.presenter;

import com.pro2on.democlean.domain.interactor.SplashInteractor;
import com.pro2on.democlean.mvp.view.SplashView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Date: 27.03.17
 * Time: 15:26
 * Created by pro2on in project DemoClean
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    private final SplashInteractor splashInteractor;

    public SplashPresenter(SplashInteractor splashInteractor) {
        this.splashInteractor = splashInteractor;
    }


    public void checkIsUserAuthorized() {

        unsubscribeOnDestroy(

                splashInteractor.isSessionIsStartedOrStartSessionIfPossible()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                Timber.d("is auth: %s", aBoolean);
                                setAuthorized(aBoolean);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Timber.e(throwable);
                                setAuthorized(false);
                            }
                        })

        );

    }


    /**
     * Just setup auth for all attached views
     * */
    private void setAuthorized(boolean isAuth) {
        for (SplashView splashView : getAttachedViews()) {
            splashView.setAuthorized(isAuth);
        }
    }
}
