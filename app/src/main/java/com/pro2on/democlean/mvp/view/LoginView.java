package com.pro2on.democlean.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface LoginView extends MvpView {

    static final String TAG_PROGRESS = "progress";
    static final String TAG_ERROR = "error";

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_PROGRESS)
    void showProgress();

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_PROGRESS)
    void hideProgress();

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_ERROR)
    void showError(String message);

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_ERROR)
    void showError(int message);

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_ERROR)
    void hideError();

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_ERROR)
    void showFieldError(Integer loginError);


    @StateStrategyType(OneExecutionStateStrategy.class)
    void successLogin();


}