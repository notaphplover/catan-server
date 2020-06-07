package io.github.notaphplover.catanserver.user.adapter.db.query;

import io.github.notaphplover.catanserver.common.FixtureFactory;

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
