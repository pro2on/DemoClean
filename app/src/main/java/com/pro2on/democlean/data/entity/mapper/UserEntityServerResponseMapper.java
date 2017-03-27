package com.pro2on.democlean.data.entity.mapper;

import com.pro2on.democlean.data.entity.UserEntity;
import com.pro2on.democlean.data.net.response.ProjectsResponse;
import com.pro2on.democlean.data.net.response.UserResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Date: 27.03.17
 * Time: 19:24
 * Created by pro2on in project DemoClean
 */
@Singleton
public class UserEntityServerResponseMapper {

    @Inject
    public UserEntityServerResponseMapper() {
    }


    public UserEntity transform(UserResponse userResponse, List<ProjectsResponse> projectsResponses) {
        UserEntity userEntity = null;
        if (userResponse != null) {

            userEntity = new UserEntity();

            userEntity.setId(userResponse.id);
            userEntity.setUrl(userResponse.avatar_url);
            userEntity.setEmail(userResponse.email);
            userEntity.setLogin(userResponse.login);


            if (projectsResponses != null) {

                List<String> projects = new ArrayList<>();

                for (ProjectsResponse pr : projectsResponses) {
                    projects.add(pr.full_name);
                }

                userEntity.setProjects(projects);
            }


        }
        return userEntity;
    }
}
