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

  public boolean equals(Object obj) {
    if (!(obj instanceof IUserTokenJwtClaims)) {
      return false;
    }

    IUserTokenJwtClaims that = (IUserTokenJwtClaims) obj;

    return that.getId() == getId() && that.getUsername().equals(getUsername());
  }

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }
}
