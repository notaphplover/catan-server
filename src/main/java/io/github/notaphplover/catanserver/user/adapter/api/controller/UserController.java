package io.github.notaphplover.catanserver.user.adapter.api.controller;

import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.reqHandler.PostUserReqHandler;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

  private PostUserReqHandler postUserReqHandler;

  public UserController(PostUserReqHandler postUserReqHandler) {
    this.postUserReqHandler = postUserReqHandler;
  }

  @PostMapping()
  IUserApi newUser(@Valid @RequestBody PostUserRequest postUserRequest) {
    return this.postUserReqHandler.handle(postUserRequest);
  }
}
