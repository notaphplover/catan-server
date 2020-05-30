package io.github.notaphplover.catanserver.user.adapter.db.query;

import java.util.Optional;

public class UserFindQueryDb {

  private Optional<Long> id;

  private Optional<String> username;

  public UserFindQueryDb(Optional<Long> id, Optional<String> username) {
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
