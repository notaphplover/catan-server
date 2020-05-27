package io.github.notaphplover.catanserver.user.adapter.api.model;

public class UserApi implements IUserApi {
  private String username;

  public UserApi(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }
}
