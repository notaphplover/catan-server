package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.model.IUser;

public class UserTokenJwtClaimsFixturesUtils {

  public static IUserTokenJwtClaims getUserTokenJwtClaims() {
    IUser user = UserFixturesUtils.getUser();

    return new UserTokenJwtClaims(user.getId(), user.getUsername());
  }
}
