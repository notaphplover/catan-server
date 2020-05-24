package io.github.notaphplover.catanserver.user.domain.query;

public class UserCreationQuery {

    private final String username;

    private final String password;

    public UserCreationQuery(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
