package io.github.notaphplover.catanserver.user.domain.query;

import java.util.Objects;

public class UserCreationQuery implements IUserCreationQuery {

  private final String username;

  private final String password;

  public UserCreationQuery(final String username, final String password) {
    this.username = username;
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof UserCreationQuery)) {
      return false;
    }

    UserCreationQuery that = (UserCreationQuery) o;

    return Objects.equals(username, that.username) && Objects.equals(password, that.password);
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
