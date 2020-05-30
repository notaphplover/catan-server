package io.github.notaphplover.catanserver.user.adapter.jwt.model;

public class UserTokenJwtClaims {
  private long id;

  private String username;

  public UserTokenJwtClaims(long id, String username) {
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
