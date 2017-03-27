package com.pro2on.democlean.data.repository.datastore;

import com.pro2on.democlean.data.cache.UserCache;
import com.pro2on.democlean.data.entity.mapper.UserEntityServerResponseMapper;
import com.pro2on.democlean.data.net.GithubApi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Date: 27.03.17
 * Time: 19:58
 * Created by pro2on in project DemoClean
 */
@Singleton
public class UserDataStoreFactory {

    private final UserCache userCache;
    private final GithubApi githubApi;
    private final UserEntityServerResponseMapper serverResponseMapper;

    @Inject
    public UserDataStoreFactory(UserCache userCache, GithubApi githubApi,
                                UserEntityServerResponseMapper serverResponseMapper) {

        this.userCache = userCache;
        this.githubApi = githubApi;
        this.serverResponseMapper = serverResponseMapper;
    }

    public UserDataStore create(String login) {
        UserDataStore userDataStore;

        if (!userCache.isExpired() && userCache.isCached(login)) {
            userDataStore = new DiskUserDataStore(this.userCache);
        } else {
            userDataStore = createCloudDataStore(login);
        }

        return userDataStore;
    }



    public UserDataStore createCloudDataStore(String login) {
        return new CloudUserDataStore(githubApi, userCache, serverResponseMapper);
    }
}
