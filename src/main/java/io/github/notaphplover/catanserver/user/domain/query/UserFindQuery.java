package io.github.notaphplover.catanserver.user.domain.query;

import java.util.Optional;

public class UserFindQuery {

  private Optional<Long> id;

  private Optional<String> username;

  public UserFindQuery(Optional<Long> id, Optional<String> username) {
    this.id = id;
    this.username = username;
  }

  public Optional<Long> getId() {
    return id;
  }

  public Optional<String> getUsername() {
    return username;
  }
}
