package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class PostUserReqHandler implements IReqHandler<PostUserRequest, IUserApi> {

  private IInteractor<UserCreationQuery, IUser> createUserInteractor;

  private IPort<PostUserRequest, UserCreationQuery> postUserRequestToUserCreationQueryPort;

  private IPort<IUser, IUserApi> userToUserApiPort;

  public PostUserReqHandler(
      IInteractor<UserCreationQuery, IUser> createUserInteractor,
      IPort<PostUserRequest, UserCreationQuery> postUserRequestToUserCreationQueryPort,
      IPort<IUser, IUserApi> userToUserApiPort) {
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
