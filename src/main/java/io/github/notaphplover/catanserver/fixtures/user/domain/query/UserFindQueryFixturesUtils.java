package io.github.notaphplover.catanserver.fixtures.user.domain.query;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;

import java.util.Optional;

public class UserFindQueryFixturesUtils {

  private static IFixtureFactory<IUserFindQuery> userFindQueryFactory =
      new UserFindQueryFixturesFactory(getUserFindQuery());

  public static IUserFindQuery getUserFindQuery() {
    IUser user = UserFixturesUtils.getUser();

    return new UserFindQuery(Optional.of(user.getId()), Optional.of(user.getUsername()));
  }

  public static IFixtureFactory<IUserFindQuery> getUserFindQueryFactory() {
    return userFindQueryFactory;
  }
}
