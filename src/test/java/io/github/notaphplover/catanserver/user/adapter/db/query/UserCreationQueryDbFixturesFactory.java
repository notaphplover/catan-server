package io.github.notaphplover.catanserver.user.adapter.db.query;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class UserCreationQueryDbFixturesFactory extends FixtureFactory<UserCreationQueryDb> {

  public UserCreationQueryDbFixturesFactory(UserCreationQueryDb entity) {
    super(entity);
  }

  @Override
  public UserCreationQueryDb get() {
    UserCreationQueryDb entity = getEntity();

    return new UserCreationQueryDb(entity.getUsername(), entity.getPasswordHash());
  }
}
