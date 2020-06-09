package io.github.notaphplover.catanserver.user.domain.model;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class UserTokenFixturesFactory extends FixtureFactory<IUserToken> {

  public UserTokenFixturesFactory(IUserToken entity) {
    super(entity);
  }

  @Override
  public IUserToken get() {
    IUserToken entity = getEntity();

    return new UserToken(entity.getId(), entity.getUsername());
  }
}
