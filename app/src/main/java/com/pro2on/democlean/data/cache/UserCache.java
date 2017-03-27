package com.pro2on.democlean.data.cache;


import com.pro2on.democlean.data.entity.UserEntity;

import io.reactivex.Observable;

/**
 * An interface representing a user Cache.
 */

public interface UserCache {


    /**
     * Gets an {@link Observable} which will emit a {@link UserEntity}.
     *
     * We store only one entity per application
     */
    Observable<UserEntity> get(String login);


    /**
     * Puts and element into the cache.
     *
     * @param userEntity Element to insert in the cache.
     */
    void put(UserEntity userEntity);


    /**
     * Evict all elements of the cache.
     */
    void clear();



    /**
     * Checks if an element (User) exists in the cache.
     *
     * @return true if the element is cached, otherwise false.
     */
    boolean isCached(String login);




    /**
     * Checks if the cache is expired.
     *
     * @return true, the cache is expired, otherwise false.
     */
    boolean isExpired();


}
