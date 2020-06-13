package io.github.notaphplover.catanserver.user.domain.query;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;

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
