package com.pro2on.democlean.data.cache;

import android.content.SharedPreferences;
import android.text.TextUtils;


import com.pro2on.democlean.data.cache.serializer.Serializer;
import com.pro2on.democlean.data.entity.UserEntity;
import com.pro2on.democlean.domain.exception.UserNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Date: 23.03.17
 * Time: 10:36
 * Created by pro2on in project GithubClean
 */
@Singleton
public class UserCacheImpl implements UserCache {


    private static final String KEY_SERIALIZED_USER = "serialized_user";
    private static final String SETTINGS_KEY_LAST_CACHE_UPDATE = "last_cache_update";


    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final SharedPreferences sharedPreferences;
    private final Serializer serializer;


    @Inject
    public UserCacheImpl(SharedPreferences sharedPreferences, Serializer serializer) {
        this.sharedPreferences = sharedPreferences;
        this.serializer = serializer;
    }

    @Override
    public Observable<UserEntity> get(final String login) {
        return Observable.create(new ObservableOnSubscribe<UserEntity>() {
            @Override
            public void subscribe(ObservableEmitter<UserEntity> emmiter) throws Exception {

                final String serializedUser = sharedPreferences.getString(KEY_SERIALIZED_USER, null);
                if (!TextUtils.isEmpty(serializedUser)) {

                    final UserEntity userEntity = serializer.deserialize(serializedUser, UserEntity.class);
                    if (userEntity != null && !TextUtils.isEmpty(userEntity.getLogin()) && userEntity.getLogin().equals(login)) {

                        emmiter.onNext(userEntity);
                        emmiter.onComplete();

                    } else {
                        emmiter.onError(new UserNotFoundException());
                    }



                } else {
                    emmiter.onError(new UserNotFoundException());
                }

            }
        });
    }

    @Override
    public void put(UserEntity userEntity) {
        if (userEntity == null) {
            this.clear();
        } else {
            final String serializedUser = serializer.serialize(userEntity, UserEntity.class);
            sharedPreferences.edit().putString(KEY_SERIALIZED_USER, serializedUser).apply();
            setLastCacheUpdateTimeMillis();
        }
    }

    @Override
    public void clear() {
        sharedPreferences.edit().remove(KEY_SERIALIZED_USER).apply();
    }

    @Override
    public boolean isCached(String login) {

        final String serializedUser = sharedPreferences.getString(KEY_SERIALIZED_USER, null);
        if (!TextUtils.isEmpty(serializedUser)) {

            final UserEntity userEntity = serializer.deserialize(serializedUser, UserEntity.class);
            if (userEntity != null && !TextUtils.isEmpty(userEntity.getLogin()) && userEntity.getLogin().equals(login)) {

                return true;

            } else {

                this.clear();

                return false;

            }


        } else {
            return false;
        }




    }

    @Override
    public boolean isExpired() {
        final long currentMillis = System.currentTimeMillis();
        final long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentMillis - lastUpdateTime) > EXPIRATION_TIME);

        if (expired) {
            this.clear();
        }

        return expired;
    }



    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        sharedPreferences.edit().putLong(SETTINGS_KEY_LAST_CACHE_UPDATE, currentMillis).apply();
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return sharedPreferences.getLong(SETTINGS_KEY_LAST_CACHE_UPDATE, 0L);
    }



}
