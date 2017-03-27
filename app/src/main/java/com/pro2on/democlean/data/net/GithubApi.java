package com.pro2on.democlean.data.net;

import com.pro2on.democlean.data.net.response.ProjectsResponse;
import com.pro2on.democlean.data.net.response.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Date: 27.03.17
 * Time: 19:27
 * Created by pro2on in project DemoClean
 */

public interface GithubApi {

    @GET("/users/{username}")
    Observable<UserResponse> getUser(@Path("username") String username
    );

    @GET("/users/{username}/repos")
    Observable<List<ProjectsResponse>> getUsersProjects(
            @Path("username") String username
    );

}
