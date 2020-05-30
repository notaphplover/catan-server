package io.github.notaphplover.catanserver.user.adapter.api.controller;

import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.GetUserRequest;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.model.IUserToken;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

  private IReqHandler<GetUserRequest, Optional<IUserApi>> getUserRequestHandler;

  private IReqHandler<PostUserRequest, IUserApi> postUserReqHandler;

  public UserController(
      IReqHandler<GetUserRequest, Optional<IUserApi>> getUserRequestHandler,
      IReqHandler<PostUserRequest, IUserApi> postUserReqHandler) {
    this.getUserRequestHandler = getUserRequestHandler;
    this.postUserReqHandler = postUserReqHandler;
  }

  @GetMapping("/me")
  Optional<IUserApi> getMyUser(Authentication authentication) {
    IUserToken userToken = (IUserToken) authentication.getPrincipal();

    GetUserRequest getUserRequest =
        new GetUserRequest(Optional.of(userToken.getId()), Optional.empty());

    return getUser(getUserRequest);
  }

  Optional<IUserApi> getUser(@Valid GetUserRequest userRequest) {
    return getUserRequestHandler.handle(userRequest);
  }

  @PostMapping()
  IUserApi newUser(@Valid @RequestBody PostUserRequest postUserRequest) {
    return this.postUserReqHandler.handle(postUserRequest);
  }
}
