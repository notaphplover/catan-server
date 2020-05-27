package io.github.notaphplover.catanserver.user.port;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.domain.interactor.HashPasswordInteractor;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class UserCreationQueryToUserCreationQueryDbPort
    implements IPort<UserCreationQuery, UserCreationQueryDb> {

  private HashPasswordInteractor hashPasswordInteractor;

  public UserCreationQueryToUserCreationQueryDbPort(HashPasswordInteractor hashPasswordInteractor) {
    this.hashPasswordInteractor = hashPasswordInteractor;
  }

  @Override
  public UserCreationQueryDb transform(UserCreationQuery input) {

    String username = input.getUsername();
    String passwordHash = hashPasswordInteractor.interact(input.getPassword());

    return new UserCreationQueryDb(username, passwordHash);
  }
}
