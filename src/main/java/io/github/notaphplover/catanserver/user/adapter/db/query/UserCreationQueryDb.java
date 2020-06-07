package io.github.notaphplover.catanserver.user.adapter.db.query;

public class UserCreationQueryDb {

  private final String username;

  private final String passwordHash;

  public UserCreationQueryDb(String username, String passwordHash) {
    this.username = username;
    this.passwordHash = passwordHash;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof UserCreationQueryDb)) {
      return false;
    }

    UserCreationQueryDb that = (UserCreationQueryDb) obj;

    return that.getUsername().equals(getUsername())
        && that.getPasswordHash().equals(getPasswordHash());
  }

  public String getUsername() {
    return username;
  }

  public String getPasswordHash() {
    return passwordHash;
  }
}
