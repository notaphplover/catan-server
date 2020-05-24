package io.github.notaphplover.catanserver.user.adapter.api.model;

public interface IUserApi {
    String getUsername();

    String getPassword();

    void setUsername(String username);

    void setPassword(String password);
}