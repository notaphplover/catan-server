package io.github.notaphplover.catanserver.fixtures.user.adapter.db.query;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;

public class UserFindQueryDbFixturesFactory extends FixtureFactory<UserFindQueryDb> {

  public UserFindQueryDbFixturesFactory(UserFindQueryDb entity) {
    super(entity);
  }

  @Override
  public UserFindQueryDb get() {
    UserFindQueryDb entity = getEntity();

    return new UserFindQueryDb(entity.getId(), entity.getUsername());
  }
}
