package io.github.notaphplover.catanserver.user.adapter.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostUserRequest {

  @Size(min = 6, max = 128)
  @NotBlank
  @NotNull
  private String username;

  @Size(min = 8, max = 128)
  @NotBlank
  @NotNull
  private String password;

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof PostUserRequest)) {
      return false;
    }

    PostUserRequest that = (PostUserRequest) obj;

    return that.getUsername().equals(getUsername()) && that.getPassword().equals(getPassword());
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
