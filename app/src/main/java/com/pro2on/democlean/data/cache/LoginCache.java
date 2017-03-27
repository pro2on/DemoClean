package com.pro2on.democlean.data.cache;

import io.reactivex.Observable;

/**
 * Date: 27.03.17
 * Time: 14:57
 * Created by pro2on in project DemoClean
 */

public interface LoginCache {

    Observable<String> get();

    void put(String login);

    void clear();

    boolean isCached();

}
