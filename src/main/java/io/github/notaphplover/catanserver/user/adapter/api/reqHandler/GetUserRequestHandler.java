package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import io.github.notaphplover.catanserver.common.adapter.api.exception.EntityNotFoundException;
import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.GetUserRequest;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetUserRequestHandler implements IReqHandler<GetUserRequest, Optional<IUserApi>> {

  private IInteractor<IUserFindQuery, Optional<IUser>> findUserInteractor;

  private IPort<IUser, IUserApi> userToUserApiPort;

  public GetUserRequestHandler(
      IInteractor<IUserFindQuery, Optional<IUser>> findUserInteractor,
      IPort<IUser, IUserApi> userToUserApiPort) {
    this.findUserInteractor = findUserInteractor;
    this.userToUserApiPort = userToUserApiPort;
  }

  @Override
  public Optional<IUserApi> handle(GetUserRequest request) {

    UserFindQuery query = new UserFindQuery(request.getId(), request.getUsername());

    Optional<IUser> userFound = findUserInteractor.interact(query);

    if (userFound.isEmpty()) {
      throw new EntityNotFoundException("User not found");
    }

    return userFound.map((IUser u) -> userToUserApiPort.transform(u));
  }
}
