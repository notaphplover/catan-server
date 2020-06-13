package io.github.notaphplover.catanserver.user.adapter.db.query;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDbFixturesUtils;

public class UserCreationQueryDbFixturesUtils {

  private static final IFixtureFactory<UserCreationQueryDb> userCreationQueryDbFactory =
      new UserCreationQueryDbFixturesFactory(getUserCreationQueryDb());

  public static UserCreationQueryDb getUserCreationQueryDb() {

    UserDb userDb = UserDbFixturesUtils.getUserDb();

    return new UserCreationQueryDb(userDb.getUsername(), userDb.getPasswordHash());
  }

  public static IFixtureFactory<UserCreationQueryDb> getUserCreationQueryDbFactory() {
    return userCreationQueryDbFactory;
  }
}
