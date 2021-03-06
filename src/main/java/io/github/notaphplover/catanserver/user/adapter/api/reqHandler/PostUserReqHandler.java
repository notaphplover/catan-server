package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class PostUserReqHandler implements IReqHandler<PostUserRequest, IUserApi> {

  private IInteractor<IUserCreationQuery, IUser> createUserInteractor;

  private IPort<PostUserRequest, IUserCreationQuery> postUserRequestToUserCreationQueryPort;

  private IPort<IUser, IUserApi> userToUserApiPort;

  public PostUserReqHandler(
      IInteractor<IUserCreationQuery, IUser> createUserInteractor,
      IPort<PostUserRequest, IUserCreationQuery> postUserRequestToUserCreationQueryPort,
      IPort<IUser, IUserApi> userToUserApiPort) {
    this.createUserInteractor = createUserInteractor;
    this.postUserRequestToUserCreationQueryPort = postUserRequestToUserCreationQueryPort;
    this.userToUserApiPort = userToUserApiPort;
  }

  @Override
  public IUserApi handle(PostUserRequest request) {
    IUserCreationQuery creationQuery = postUserRequestToUserCreationQueryPort.transform(request);
    IUser userCreated = createUserInteractor.interact(creationQuery);
    return userToUserApiPort.transform(userCreated);
  }
}
