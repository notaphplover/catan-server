package io.github.notaphplover.catanserver.user.adapter.db.repository;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import io.github.notaphplover.catanserver.user.domain.repository.IUserCreationRepository;
import io.github.notaphplover.catanserver.user.domain.repository.IUserSearchRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryManager implements IUserCreationRepository, IUserSearchRepository {

  private IJPAUserRepository innerRepository;

  private IPort<IUserCreationQuery, UserCreationQueryDb> userCreationQueryToUserCreationQueryDbPort;

  private IPort<UserDb, IUser> userDbToUserPort;

  private IPort<IUserFindQuery, UserFindQueryDb> userFindQueryToUserFindQueryDbPort;

  public UserRepositoryManager(
      IJPAUserRepository innerRepository,
      IPort<IUserCreationQuery, UserCreationQueryDb> userCreationQueryToUserCreationQueryDbPort,
      IPort<UserDb, IUser> userDbToUserPort,
      IPort<IUserFindQuery, UserFindQueryDb> userFindQueryToUserFindQueryDbPort) {
    this.innerRepository = innerRepository;
    this.userCreationQueryToUserCreationQueryDbPort = userCreationQueryToUserCreationQueryDbPort;
    this.userDbToUserPort = userDbToUserPort;
    this.userFindQueryToUserFindQueryDbPort = userFindQueryToUserFindQueryDbPort;
  }

  public List<IUser> createMany(Collection<IUserCreationQuery> queries) {

    List<UserDb> usersDbToCreate =
        queries.stream()
            .map(
                (IUserCreationQuery query) -> {
                  UserCreationQueryDb queryDb =
                      userCreationQueryToUserCreationQueryDbPort.transform(query);
                  return getUserDbFromUserCreationQueryDb(queryDb);
                })
            .collect(Collectors.toList());

    List<UserDb> usersDbCreated = this.innerRepository.saveAll(usersDbToCreate);

    List<IUser> usersList =
        usersDbCreated.stream()
            .map((UserDb userDb) -> userDbToUserPort.transform(userDb))
            .collect(Collectors.toList());

    return usersList;
  }

  public IUser createOne(IUserCreationQuery query) {
    UserCreationQueryDb queryDb = userCreationQueryToUserCreationQueryDbPort.transform(query);

    UserDb userDb = getUserDbFromUserCreationQueryDb(queryDb);

    UserDb userCreated = this.innerRepository.save(userDb);

    return userDbToUserPort.transform(userCreated);
  }

  public Collection<IUser> findMany(IUserFindQuery query) {
    UserFindQueryDb queryDb = userFindQueryToUserFindQueryDbPort.transform(query);

    List<UserDb> userDbFound =
        this.innerRepository.findAll(UserSpecifications.compliantWith(queryDb));

    return userDbFound.stream()
        .map((UserDb userDb) -> userDbToUserPort.transform(userDb))
        .collect(Collectors.toList());
  }

  public Optional<IUser> findOne(IUserFindQuery query) {
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
