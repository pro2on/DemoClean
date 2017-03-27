package com.pro2on.democlean.di.module;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pro2on.democlean.data.net.GithubApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Date: 04.03.17
 * Time: 09:39
 * Created by pro2on in project Manalikes
 */
@Module
public class ApiModule {


    private static final String BASE_URL = "https://api.github.com";


    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }



    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptro() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }



    @Singleton
    @Provides
    OkHttpClient provideHttpClient(HttpLoggingInterceptor loggingInterceptor) {

        return new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

    }



    @Provides
    @Singleton
    Retrofit provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                             OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .build();

    }



    @Provides
    @Singleton
    GithubApi provideApiService(Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }
}
