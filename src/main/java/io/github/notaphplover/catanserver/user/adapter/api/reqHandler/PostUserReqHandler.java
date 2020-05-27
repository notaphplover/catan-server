package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.interactor.CreateUserInteractor;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import io.github.notaphplover.catanserver.user.port.PostUserRequestToUserCreationQueryPort;
import io.github.notaphplover.catanserver.user.port.UserToUserApiPort;
import org.springframework.stereotype.Service;

@Service
public class PostUserReqHandler implements IReqHandler<PostUserRequest, IUserApi> {

  private CreateUserInteractor createUserInteractor;

  private PostUserRequestToUserCreationQueryPort postUserRequestToUserCreationQueryPort;

  private UserToUserApiPort userToUserApiPort;

  public PostUserReqHandler(
      CreateUserInteractor createUserInteractor,
      PostUserRequestToUserCreationQueryPort postUserRequestToUserCreationQueryPort,
      UserToUserApiPort userToUserApiPort) {
    this.createUserInteractor = createUserInteractor;
    this.postUserRequestToUserCreationQueryPort = postUserRequestToUserCreationQueryPort;
    this.userToUserApiPort = userToUserApiPort;
  }

  @Override
  public IUserApi handle(PostUserRequest request) {
    UserCreationQuery creationQuery = postUserRequestToUserCreationQueryPort.transform(request);
    IUser userCreated = createUserInteractor.interact(creationQuery);
    return userToUserApiPort.transform(userCreated);
  }
}
