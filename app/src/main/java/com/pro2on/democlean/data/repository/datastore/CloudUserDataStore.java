package com.pro2on.democlean.data.repository.datastore;

import com.pro2on.democlean.data.cache.UserCache;
import com.pro2on.democlean.data.entity.UserEntity;
import com.pro2on.democlean.data.entity.mapper.UserEntityServerResponseMapper;
import com.pro2on.democlean.data.net.GithubApi;
import com.pro2on.democlean.data.net.response.ProjectsResponse;
import com.pro2on.democlean.data.net.response.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Date: 27.03.17
 * Time: 19:50
 * Created by pro2on in project DemoClean
 */

public class CloudUserDataStore implements UserDataStore {

    private final GithubApi githubApi;
    private final UserCache userCache;
    private final UserEntityServerResponseMapper serverResponseMapper;


    public CloudUserDataStore(GithubApi githubApi, UserCache userCache, UserEntityServerResponseMapper serverResponseMapper) {
        this.githubApi = githubApi;
        this.userCache = userCache;
        this.serverResponseMapper = serverResponseMapper;
    }

    @Override
    public Observable<UserEntity> get(String login) {
        return Observable.combineLatest(githubApi.getUser(login), githubApi.getUsersProjects(login), new BiFunction<UserResponse, List<ProjectsResponse>, UserEntity>() {
                    @Override
                    public UserEntity apply(@NonNull UserResponse userResponse,
                                            @NonNull List<ProjectsResponse> projectsResponses) throws Exception {
                        return serverResponseMapper.transform(userResponse, projectsResponses);
                    }
                })
                .doOnNext(new Consumer<UserEntity>() {
                    @Override
                    public void accept(@NonNull UserEntity userEntity) throws Exception {
                        userCache.put(userEntity);
                    }
                });
    }

}
