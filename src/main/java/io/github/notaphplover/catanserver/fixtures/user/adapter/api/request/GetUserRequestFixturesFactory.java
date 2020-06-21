package io.github.notaphplover.catanserver.fixtures.user.adapter.api.request;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.api.request.GetUserRequest;

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
