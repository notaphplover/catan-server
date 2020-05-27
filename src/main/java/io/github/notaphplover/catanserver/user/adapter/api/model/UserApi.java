package io.github.notaphplover.catanserver.user.adapter.api.model;

public class UserApi implements IUserApi {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
