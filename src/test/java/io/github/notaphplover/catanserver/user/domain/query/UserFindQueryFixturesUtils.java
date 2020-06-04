package io.github.notaphplover.catanserver.user.domain.query;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import java.util.Optional;

public class UserFindQueryFixturesUtils {

  private static IFixtureFactory<UserFindQuery> userFindQueryFactory =
      new UserFindQueryFixturesFactory(getUserFindQuery());

  public static UserFindQuery getUserFindQuery() {
    IUser user = UserFixturesUtils.getUser();

    return new UserFindQuery(Optional.of(user.getId()), Optional.of(user.getUsername()));
  }

  public static IFixtureFactory<UserFindQuery> getUserFindQueryFactory() {
    return userFindQueryFactory;
  }
}
