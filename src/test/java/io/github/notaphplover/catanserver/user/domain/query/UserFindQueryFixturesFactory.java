package io.github.notaphplover.catanserver.user.domain.query;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class UserFindQueryFixturesFactory extends FixtureFactory<UserFindQuery> {

  public UserFindQueryFixturesFactory(UserFindQuery entity) {
    super(entity);
  }

  @Override
  public UserFindQuery get() {

    UserFindQuery entity = getEntity();

    return new UserFindQuery(entity.getId(), entity.getUsername());
  }
}
