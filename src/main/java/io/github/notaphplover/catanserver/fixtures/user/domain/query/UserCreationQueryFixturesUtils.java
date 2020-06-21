package io.github.notaphplover.catanserver.fixtures.user.domain.query;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;

public class UserCreationQueryFixturesUtils {

  private static IFixtureFactory<IUserCreationQuery> userCreationQueryFactory =
      new UserCreationQueryFixturesFactory(getUserCreationQuery());

  public static IUserCreationQuery getUserCreationQuery() {
    IUser user = UserFixturesUtils.getUser();
    return new UserCreationQuery(user.getUsername(), "password");
  }

  public static IFixtureFactory<IUserCreationQuery> getUserCreationQueryFactory() {
    return userCreationQueryFactory;
  }
}
