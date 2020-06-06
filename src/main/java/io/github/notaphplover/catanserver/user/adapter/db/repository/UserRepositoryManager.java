package io.github.notaphplover.catanserver.user.adapter.db.repository;

import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.User;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import io.github.notaphplover.catanserver.user.port.db.UserCreationQueryToUserCreationQueryDbPort;
import io.github.notaphplover.catanserver.user.port.db.UserFindQueryToUserFindQueryDbPort;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryManager {

  private IUserRepository innerRepository;

  private UserCreationQueryToUserCreationQueryDbPort userCreationQueryToUserCreationQueryDbPort;

  private UserFindQueryToUserFindQueryDbPort userFindQueryToUserFindQueryDbPort;

  public UserRepositoryManager(
      IUserRepository innerRepository,
      UserCreationQueryToUserCreationQueryDbPort userCreationQueryToUserCreationQueryDbPort,
      UserFindQueryToUserFindQueryDbPort userFindQueryToUserFindQueryDbPort) {
    this.innerRepository = innerRepository;
    this.userCreationQueryToUserCreationQueryDbPort = userCreationQueryToUserCreationQueryDbPort;
    this.userFindQueryToUserFindQueryDbPort = userFindQueryToUserFindQueryDbPort;
  }

  public IUser create(UserCreationQuery query) {
    UserCreationQueryDb queryDb = userCreationQueryToUserCreationQueryDbPort.transform(query);

    UserDb userDb = getUserDbFromUserCreationQueryDb(queryDb);

    UserDb userCreated = this.innerRepository.save(userDb);

    return userDbToUser(userCreated);
  }

  public Optional<IUser> find(UserFindQuery query) {
    UserFindQueryDb queryDb = userFindQueryToUserFindQueryDbPort.transform(query);

    Optional<UserDb> userDbCapsule =
        this.innerRepository.findOne(UserSpecifications.compliantWith(queryDb));

    return userDbCapsule.map((UserDb userDb) -> userDbToUser(userDb));
  }

  private UserDb getUserDbFromUserCreationQueryDb(UserCreationQueryDb queryDb) {
    UserDb userDb = new UserDb();
    userDb.setUsername(queryDb.getUsername());
    userDb.setPasswordHash(queryDb.getPasswordHash());

    return userDb;
  }

  private IUser userDbToUser(UserDb userDb) {
    IUser user = new User(userDb.getId());
    user.setPasswordHash(userDb.getPasswordHash());
    user.setUsername(userDb.getUsername());

    return user;
  }
}
