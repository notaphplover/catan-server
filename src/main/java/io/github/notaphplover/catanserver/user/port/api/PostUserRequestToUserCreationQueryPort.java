package io.github.notaphplover.catanserver.user.port.api;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class PostUserRequestToUserCreationQueryPort
    implements IPort<PostUserRequest, IUserCreationQuery> {

  @Override
  public IUserCreationQuery transform(PostUserRequest input) {
    return new UserCreationQuery(input.getUsername(), input.getPassword());
  }
}
