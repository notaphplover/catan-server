package io.github.notaphplover.catanserver.fixtures.user.domain.query;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;

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
