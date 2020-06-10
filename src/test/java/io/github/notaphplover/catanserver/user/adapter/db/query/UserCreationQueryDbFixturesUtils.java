package io.github.notaphplover.catanserver.user.adapter.db.query;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQueryFixturesUtils;

public class UserCreationQueryDbFixturesUtils {

  private static final IFixtureFactory<UserCreationQueryDb> userCreationQueryDbFactory =
      new UserCreationQueryDbFixturesFactory(getUserCreationQueryDb());

  public static UserCreationQueryDb getUserCreationQueryDb() {
    IUserCreationQuery userCreationQuery =
        UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get();

    return new UserCreationQueryDb(
        userCreationQuery.getUsername(), userCreationQuery.getPassword());
  }

  public static IFixtureFactory<UserCreationQueryDb> getUserCreationQueryDbFactory() {
    return userCreationQueryDbFactory;
  }
}
