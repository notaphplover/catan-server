package io.github.notaphplover.catanserver.user.domain.model;

public class User implements IUser {

  private Long id;

  private String username;

  private String passwordHash;

  public User(Long id) {
    this.id = id;
  }

  public User() {
    this(null);
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public String getPasswordHash() {
    return passwordHash;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }
}
