package com.pro2on.democlean.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.pro2on.democlean.R;
import com.pro2on.democlean.application.DemoApp;
import com.pro2on.democlean.domain.interactor.LogoutInteractor;
import com.pro2on.democlean.mvp.presenter.LogoutPresenter;
import com.pro2on.democlean.mvp.view.LogoutView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Date: 27.03.17
 * Time: 12:12
 * Created by pro2on in project DemoClean
 */

public class MainActivity extends MvpAppCompatActivity implements LogoutView {


    private static final int LAYOUT = R.layout.activity_main;



    @Inject
    LogoutInteractor logoutInteractor;

    @InjectPresenter
    LogoutPresenter logoutPresenter;


    @ProvidePresenter LogoutPresenter provideLogoutPresenter() {
        return new LogoutPresenter(logoutInteractor);
    }


    @BindView(R.id.lvRepositories)
    ListView lvRepositories;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;


    private AlertDialog errorDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((DemoApp)getApplication()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(LAYOUT);
        ButterKnife.bind(this);



        errorDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .create();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_item_exit) {
            logoutPresenter.onLogout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onLogout() {
        Timber.d("need to logout");
        final Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}
