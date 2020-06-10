package io.github.notaphplover.catanserver.user.domain.query;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class UserCreationQueryFixturesFactory extends FixtureFactory<IUserCreationQuery> {

  public UserCreationQueryFixturesFactory(IUserCreationQuery entity) {
    super(entity);
  }

  @Override
  public IUserCreationQuery get() {
    IUserCreationQuery entity = getEntity();

    return new UserCreationQuery(entity.getUsername(), entity.getPassword());
  }
}
