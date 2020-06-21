package io.github.notaphplover.catanserver.fixtures.user.adapter.db.query;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import io.github.notaphplover.catanserver.fixtures.user.domain.query.UserFindQueryFixturesUtils;

public class UserFindQueryDbFixturesUtils {

  private static IFixtureFactory<UserFindQueryDb> userFindQueryDbFactory =
      new UserFindQueryDbFixturesFactory(getUserFindQueryDb());

  public static UserFindQueryDb getUserFindQueryDb() {
    IUserFindQuery userFindQuery = UserFindQueryFixturesUtils.getUserFindQueryFactory().get();

    return new UserFindQueryDb(userFindQuery.getId(), userFindQuery.getUsername());
  }

  public static IFixtureFactory<UserFindQueryDb> getUserFindQueryDbFactory() {
    return userFindQueryDbFactory;
  }
}
