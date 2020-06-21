package io.github.notaphplover.catanserver.fixtures.user.domain.model;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.IUserToken;
import io.github.notaphplover.catanserver.user.domain.model.UserToken;

public class UserTokenFixturesUtils {

  private static IFixtureFactory<IUserToken> userTokenFactory =
      new UserTokenFixturesFactory(getUserToken());

  public static IUserToken getUserToken() {
    IUser user = UserFixturesUtils.getUser();

    return new UserToken(user.getId(), user.getUsername());
  }

  public static IFixtureFactory<IUserToken> getUserTokenFactory() {
    return userTokenFactory;
  }
}
