package io.github.notaphplover.catanserver.user.adapter.api.request;

import io.github.notaphplover.catanserver.common.FixtureFactory;

public class GetUserRequestFixturesFactory extends FixtureFactory<GetUserRequest> {

  public GetUserRequestFixturesFactory(GetUserRequest entity) {
    super(entity);
  }

  @Override
  public GetUserRequest get() {
    GetUserRequest entity = getEntity();

    return new GetUserRequest(entity.getId(), entity.getUsername());
  }
}
