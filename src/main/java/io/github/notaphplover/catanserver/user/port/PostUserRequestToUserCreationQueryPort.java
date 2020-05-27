package io.github.notaphplover.catanserver.user.port;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class PostUserRequestToUserCreationQueryPort
    implements IPort<PostUserRequest, UserCreationQuery> {

  @Override
  public UserCreationQuery transform(PostUserRequest input) {
    return new UserCreationQuery(input.getUsername(), input.getPassword());
  }
}
