package io.github.notaphplover.catanserver.fixtures.user.adapter.api.request;

import io.github.notaphplover.catanserver.fixtures.FixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;

public class PostUserRequestFixturesFactory extends FixtureFactory<PostUserRequest> {

  public PostUserRequestFixturesFactory(PostUserRequest entity) {
    super(entity);
  }

  @Override
  public PostUserRequest get() {

    PostUserRequest entity = getEntity();

    PostUserRequest newEntity = new PostUserRequest();
    newEntity.setPassword(entity.getPassword());
    newEntity.setUsername(entity.getUsername());

    return newEntity;
  }
}
