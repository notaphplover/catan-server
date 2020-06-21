package io.github.notaphplover.catanserver.fixtures.user.adapter.api.model;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.model.UserApi;

public class UserApiFixturesFactory extends FixtureFactory<IUserApi> {

  public UserApiFixturesFactory(IUserApi entity) {
    super(entity);
  }

  @Override
  public IUserApi get() {

    IUserApi entity = getEntity();

    return new UserApi(entity.getUsername(), entity.getToken());
  }
}
