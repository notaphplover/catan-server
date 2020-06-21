package io.github.notaphplover.catanserver.user.adapter.jwt.model;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;

public class UserTokenJwtClaimsFixturesFactory extends FixtureFactory<IUserTokenJwtClaims> {

  public UserTokenJwtClaimsFixturesFactory(IUserTokenJwtClaims entity) {
    super(entity);
  }

  @Override
  public IUserTokenJwtClaims get() {
    IUserTokenJwtClaims entity = getEntity();

    return new UserTokenJwtClaims(entity.getId(), entity.getUsername());
  }
}
