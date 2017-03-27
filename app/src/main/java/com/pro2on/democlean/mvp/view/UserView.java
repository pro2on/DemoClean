package com.pro2on.democlean.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

/**
 * Date: 27.03.17
 * Time: 20:25
 * Created by pro2on in project DemoClean
 */

public interface UserView extends MvpView {


    void showUserName(String username);


    static final String TAG_PROGRESS = "progress";
    static final String TAG_ERROR = "error";


    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_PROGRESS)
    void showProgress();

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_PROGRESS)
    void hideProgress();

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_ERROR)
    void showError(String message);

    @StateStrategyType(value = AddToEndSingleStrategy.class, tag = TAG_ERROR)
    void hideError();

    void setupRepositories(List<String> items);


}
