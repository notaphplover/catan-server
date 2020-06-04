package io.github.notaphplover.catanserver.user.adapter.api.model;

public class UserApi implements IUserApi {
  private String token;

  private String username;

  public UserApi(String username, String token) {
    this.token = token;
    this.username = username;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IUserApi)) {
      return false;
    }

    return ((IUserApi) obj).getToken().equals(getToken())
        && ((IUserApi) obj).getUsername().equals(getUsername());
  }

  public String getToken() {
    return token;
  }

  public String getUsername() {
    return username;
  }
}
