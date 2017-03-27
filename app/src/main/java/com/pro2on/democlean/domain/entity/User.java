package com.pro2on.democlean.domain.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {



    private String login = "";
    private long id;
    private String url = "";
    private String email = "";
    private List<String> projects = new ArrayList<>();


    public User() {
        //empty
    }


    public User(long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", id=").append(id);
        sb.append(", url='").append(url).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", projects=").append(projects);
        sb.append('}');
        return sb.toString();
    }
}
