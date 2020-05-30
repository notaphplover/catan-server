package io.github.notaphplover.catanserver.user.adapter.api.request;

import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GetUserRequest {

  @Min(0)
  private Optional<Long> id;

  @Size(min = 6, max = 128)
  @NotBlank
  private Optional<String> username;

  public GetUserRequest(Optional<Long> id, Optional<String> username) {
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
