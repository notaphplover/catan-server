package io.github.notaphplover.catanserver.fixtures.user.adapter.db.query;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;

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
