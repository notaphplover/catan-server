package io.github.notaphplover.catanserver.user.domain.query;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class UserCreationQueryFixturesFactory extends FixtureFactory<UserCreationQuery> {

  public UserCreationQueryFixturesFactory(UserCreationQuery entity) {
    super(entity);
  }

  @Override
  public UserCreationQuery get() {
    UserCreationQuery entity = getEntity();

    return new UserCreationQuery(entity.getUsername(), entity.getPassword());
  }
}
