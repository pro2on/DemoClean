package com.pro2on.democlean.domain.repository;

import io.reactivex.Observable;

/**
 * Only local cached data
 */

public interface LoginRepository {


    Observable<String> get();

    void put(String login);

    void clear();

    boolean isCached();
}