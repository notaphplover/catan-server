package io.github.notaphplover.catanserver.user.domain.model;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class UserFixturesFactory extends FixtureFactory<IUser> {

  public UserFixturesFactory(IUser entity) {
    super(entity);
  }

  @Override
  public IUser get() {
    IUser entity = getEntity();

    IUser userFixture = new User(entity.getId());
    userFixture.setUsername(entity.getUsername());
    userFixture.setPasswordHash(entity.getPasswordHash());

    return userFixture;
  }
}
