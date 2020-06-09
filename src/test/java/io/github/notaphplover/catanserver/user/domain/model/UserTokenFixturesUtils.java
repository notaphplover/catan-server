package io.github.notaphplover.catanserver.user.domain.model;

import io.github.notaphplover.catanserver.common.IFixtureFactory;

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
