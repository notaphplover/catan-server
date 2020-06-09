package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;

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
