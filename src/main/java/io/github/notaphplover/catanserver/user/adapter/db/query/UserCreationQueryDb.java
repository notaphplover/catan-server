package io.github.notaphplover.catanserver.user.adapter.db.query;

public class UserCreationQueryDb {

  private final String username;

  private final String passwordHash;

  public UserCreationQueryDb(String username, String passwordHash) {
    this.username = username;
    this.passwordHash = passwordHash;
  }

  public String getUsername() {
    return username;
  }

  public String getPasswordHash() {
    return passwordHash;
  }
}
