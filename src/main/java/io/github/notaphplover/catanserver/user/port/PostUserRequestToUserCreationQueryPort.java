package io.github.notaphplover.catanserver.user.port;

import org.springframework.stereotype.Service;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;

@Service
public class PostUserRequestToUserCreationQueryPort implements IPort<PostUserRequest, UserCreationQuery> {

    @Override
    public UserCreationQuery transform(PostUserRequest input) {
        return new UserCreationQuery(input.getUsername(), input.getPassword());
    }
    
}