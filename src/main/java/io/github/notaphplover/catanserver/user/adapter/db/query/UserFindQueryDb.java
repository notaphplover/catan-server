package io.github.notaphplover.catanserver.user.adapter.db.query;

import java.util.Optional;

public class UserFindQueryDb {

  private Optional<Long> id;

  private Optional<String> username;

  public UserFindQueryDb(Optional<Long> id, Optional<String> username) {
    this.id = id;
    this.username = username;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof UserFindQueryDb)) {
      return false;
    }

    UserFindQueryDb that = (UserFindQueryDb) obj;

    return that.getId() == getId() && that.getUsername().equals(getUsername());
  }

  public Optional<Long> getId() {
    return id;
  }

  public Optional<String> getUsername() {
    return username;
  }
}
