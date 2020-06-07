package io.github.notaphplover.catanserver.user.adapter.db.query;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQueryFixturesUtils;

public class UserFindQueryDbFixturesUtils {

  private static IFixtureFactory<UserFindQueryDb> userFindQueryDbFactory =
      new UserFindQueryDbFixturesFactory(getUserFindQueryDb());

  public static UserFindQueryDb getUserFindQueryDb() {
    UserFindQuery userFindQuery = UserFindQueryFixturesUtils.getUserFindQueryFactory().get();

    return new UserFindQueryDb(userFindQuery.getId(), userFindQuery.getUsername());
  }

  public static IFixtureFactory<UserFindQueryDb> getUserFindQueryDbFactory() {
    return userFindQueryDbFactory;
  }
}
