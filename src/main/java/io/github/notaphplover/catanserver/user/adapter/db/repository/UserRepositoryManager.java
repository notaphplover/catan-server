package io.github.notaphplover.catanserver.user.adapter.db.repository;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryManager {

  private IUserRepository innerRepository;

  private IPort<UserCreationQuery, UserCreationQueryDb> userCreationQueryToUserCreationQueryDbPort;

  private IPort<UserDb, IUser> userDbToUserPort;

  private IPort<UserFindQuery, UserFindQueryDb> userFindQueryToUserFindQueryDbPort;

  public UserRepositoryManager(
      IUserRepository innerRepository,
      IPort<UserCreationQuery, UserCreationQueryDb> userCreationQueryToUserCreationQueryDbPort,
      IPort<UserDb, IUser> userDbToUserPort,
      IPort<UserFindQuery, UserFindQueryDb> userFindQueryToUserFindQueryDbPort) {
    this.innerRepository = innerRepository;
    this.userCreationQueryToUserCreationQueryDbPort = userCreationQueryToUserCreationQueryDbPort;
    this.userDbToUserPort = userDbToUserPort;
    this.userFindQueryToUserFindQueryDbPort = userFindQueryToUserFindQueryDbPort;
  }

  public IUser create(UserCreationQuery query) {
    UserCreationQueryDb queryDb = userCreationQueryToUserCreationQueryDbPort.transform(query);

    UserDb userDb = getUserDbFromUserCreationQueryDb(queryDb);

    UserDb userCreated = this.innerRepository.save(userDb);

    return userDbToUserPort.transform(userCreated);
  }

  public Optional<IUser> find(UserFindQuery query) {
    UserFindQueryDb queryDb = userFindQueryToUserFindQueryDbPort.transform(query);

    Optional<UserDb> userDbCapsule =
        this.innerRepository.findOne(UserSpecifications.compliantWith(queryDb));

    return userDbCapsule.map((UserDb userDb) -> userDbToUserPort.transform(userDb));
  }

  private UserDb getUserDbFromUserCreationQueryDb(UserCreationQueryDb queryDb) {
    UserDb userDb = new UserDb();
    userDb.setUsername(queryDb.getUsername());
    userDb.setPasswordHash(queryDb.getPasswordHash());

    return userDb;
  }
}
