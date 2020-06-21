package io.github.notaphplover.catanserver.fixtures.user.domain.model;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUserToken;
import io.github.notaphplover.catanserver.user.domain.model.UserToken;

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
