package io.github.notaphplover.catanserver.user.domain.query;

public class UserFindQuery {
    String username;

    public UserFindQuery(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}