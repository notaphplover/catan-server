package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.model.IUser;

public class UserTokenJwtFixturesUtils {

  private static final IFixtureFactory<IUserTokenJwt> userTokenJwtFactory =
      new UserTokenJwtFixturesFactory(getUserTokenJwt());

  public static IUserTokenJwt getUserTokenJwt() {

    IUser user = UserFixturesUtils.getUser();
    IUserTokenJwtClaims claims = UserTokenJwtClaimsFixturesUtils.getUserTokenJwtClaims();

    return new UserTokenJwt(user.getUsername(), claims);
  }

  public static IFixtureFactory<IUserTokenJwt> getUserTokenJwtFactory() {
    return userTokenJwtFactory;
  }
}
