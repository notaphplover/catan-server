package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;

public class UserTokenJwtClaimsFixturesUtils {

  public static IUserTokenJwtClaims getUserTokenJwtClaims() {
    IUser user = UserFixturesUtils.getUser();

    return new UserTokenJwtClaims(user.getId(), user.getUsername());
  }
}
