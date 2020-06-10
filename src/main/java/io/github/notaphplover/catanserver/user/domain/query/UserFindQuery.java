package io.github.notaphplover.catanserver.user.domain.query;

import java.util.Optional;

public class UserFindQuery implements IUserFindQuery {

  private Optional<Long> id;

  private Optional<String> username;

  public UserFindQuery(Optional<Long> id, Optional<String> username) {
    this.id = id;
    this.username = username;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IUserFindQuery)) {
      return false;
    }

    return ((IUserFindQuery) obj).getId().equals(getId())
        && ((IUserFindQuery) obj).getUsername().equals(getUsername());
  }

  public Optional<Long> getId() {
    return id;
  }

  public Optional<String> getUsername() {
    return username;
  }
}
