package io.github.notaphplover.catanserver.fixtures.user.adapter.db.model;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;

public class UserDbFixturesUtils {

  private static IFixtureFactory<UserDb> userDbFactory = new UserDbFixturesFactory(getUserDb());

  public static UserDb getUserDb() {
    IUser user = UserFixturesUtils.getUserFactory().get();

    UserDb userDb = new UserDb(user.getId());
    userDb.setUsername(user.getUsername());
    userDb.setPasswordHash("$2a$10$a0ATFPbhB7CM6bDQL7/sjO0v4WGUKo0B.Pj/KqwmwNhXA.6Vb.JEO");

    return userDb;
  }

  public static IFixtureFactory<UserDb> getUserDbFactory() {
    return userDbFactory;
  }
}
