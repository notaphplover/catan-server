package io.github.notaphplover.catanserver.fixtures.user.adapter.db.model;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;

public class UserDbFixturesFactory extends FixtureFactory<UserDb> {

  public UserDbFixturesFactory(UserDb entity) {
    super(entity);
  }

  @Override
  public UserDb get() {

    UserDb entity = getEntity();

    UserDb fixture = new UserDb(entity.getId());

    fixture.setUsername(entity.getUsername());
    fixture.setPasswordHash(entity.getPasswordHash());

    return fixture;
  }
}
