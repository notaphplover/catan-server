package io.github.notaphplover.catanserver.user.domain.model;

public interface IUser {
    Long getId();

    String getPasswordHash();

    String getUsername();

    void setPasswordHash(String passwordHash);

    void setUsername(String username);

}