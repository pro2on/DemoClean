package com.pro2on.democlean.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * User Entity used in the data layer
 */

public class UserEntity {

    @SerializedName("id")
    private long id;

    @SerializedName("login")
    private String login;

    @SerializedName("image_url")
    private String url;

    @SerializedName("email")
    private String email;

    @SerializedName("projects")
    private List<String> projects;


    public UserEntity() {
        // empty
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserEntity{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
