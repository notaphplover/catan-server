package io.github.notaphplover.catanserver.user.adapter.api.request;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import java.util.Optional;

public class GetUserRequestFixturesUtils {

  private static IFixtureFactory<GetUserRequest> getUserRequestFactory =
      new GetUserRequestFixturesFactory(getUserRequest());

  public static GetUserRequest getUserRequest() {

    IUser user = UserFixturesUtils.getUser();

    return new GetUserRequest(Optional.of(user.getId()), Optional.of(user.getUsername()));
  }

  public static IFixtureFactory<GetUserRequest> getUserRequestFactory() {
    return getUserRequestFactory;
  }
}
