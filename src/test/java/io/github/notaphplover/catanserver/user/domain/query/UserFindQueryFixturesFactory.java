package io.github.notaphplover.catanserver.user.domain.query;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class UserFindQueryFixturesFactory extends FixtureFactory<IUserFindQuery> {

  public UserFindQueryFixturesFactory(IUserFindQuery entity) {
    super(entity);
  }

  @Override
  public IUserFindQuery get() {

    IUserFindQuery entity = getEntity();

    return new UserFindQuery(entity.getId(), entity.getUsername());
  }
}
