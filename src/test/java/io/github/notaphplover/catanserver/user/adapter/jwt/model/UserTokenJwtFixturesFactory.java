package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;

public class UserTokenJwtFixturesFactory extends FixtureFactory<IUserTokenJwt> {

  public UserTokenJwtFixturesFactory(IUserTokenJwt entity) {
    super(entity);
  }

  @Override
  public IUserTokenJwt get() {
    IUserTokenJwt entity = getEntity();

    IUserTokenJwtClaims fixtureClaims =
        new UserTokenJwtClaimsFixturesFactory(entity.getClaims()).get();

    return new UserTokenJwt(entity.getSubject(), fixtureClaims);
  }
}
