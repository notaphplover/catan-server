package io.github.notaphplover.catanserver.user.domain.model;

public class UserToken implements IUserToken {
    
    private long id;

    private String username;
    
    public UserToken(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}