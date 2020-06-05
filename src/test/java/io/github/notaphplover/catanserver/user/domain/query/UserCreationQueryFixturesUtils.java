package io.github.notaphplover.catanserver.user.domain.query;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;

public class UserCreationQueryFixturesUtils {

  private static IFixtureFactory<UserCreationQuery> userCreationQueryFactory =
      new UserCreationQueryFixturesFactory(getUserCreationQuery());

  public static UserCreationQuery getUserCreationQuery() {
    IUser user = UserFixturesUtils.getUser();
    return new UserCreationQuery(
        user.getUsername(),
        "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoic2FtcGxlczMiLCJleHAiOjU5NTkwOTQwMzE4LCJpYXQiOjE1OTA4NTM5MTh9.yJbuBMOoujDDWS0teH1orAhkulpCBsAiU5ydIr_Yfy_Y-9MpKKrpctS7WeFn1QzRXgVXGvsZon0Jr7ZnkrmAUg");
  }

  public static IFixtureFactory<UserCreationQuery> getUserCreationQueryFactory() {
    return userCreationQueryFactory;
  }
}
