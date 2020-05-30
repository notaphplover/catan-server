package io.github.notaphplover.catanserver.user.adapter.api.model;

public class UserApi implements IUserApi {
  private String token;

  private String username;

  public UserApi(String username, String token) {
    this.token = token;
    this.username = username;
  }

  public String getToken() {
    return token;
  }

  public String getUsername() {
    return username;
  }
}
