package io.github.notaphplover.catanserver.user.adapter.db.query;

public class UserFindQueryDb {
    private String username;

    public UserFindQueryDb(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}