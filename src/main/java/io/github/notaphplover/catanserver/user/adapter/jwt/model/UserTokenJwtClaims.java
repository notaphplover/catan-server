package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import javax.persistence.Entity;

@Entity
public class UserTokenJwtClaims implements IUserTokenJwtClaims {
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
