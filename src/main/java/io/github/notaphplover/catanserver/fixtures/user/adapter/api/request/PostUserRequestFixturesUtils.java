package io.github.notaphplover.catanserver.fixtures.user.adapter.api.request;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.fixtures.user.domain.query.UserCreationQueryFixturesUtils;

public class PostUserRequestFixturesUtils {

  private static IFixtureFactory<PostUserRequest> postUserRequestFactory =
      new PostUserRequestFixturesFactory(postUserRequest());

  public static PostUserRequest postUserRequest() {

    IUserCreationQuery userCreationQuery =
        UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get();

    PostUserRequest postUserRequest = new PostUserRequest();
    postUserRequest.setPassword(userCreationQuery.getPassword());
    postUserRequest.setUsername(userCreationQuery.getUsername());

    return postUserRequest;
  }

  public static IFixtureFactory<PostUserRequest> postUserRequestFactory() {
    return postUserRequestFactory;
  }
}
