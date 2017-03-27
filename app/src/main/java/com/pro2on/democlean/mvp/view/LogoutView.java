package com.pro2on.democlean.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Date: 27.03.17
 * Time: 16:20
 * Created by pro2on in project DemoClean
 */

public interface LogoutView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onLogout();

}
