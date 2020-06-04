package io.github.notaphplover.catanserver.user.adapter.model;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.model.UserApi;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;

public class UserApiFixturesUtils {

  private static IFixtureFactory<IUserApi> userApiFactory =
      new UserApiFixturesFactory(getUserApi());

  public static IUserApi getUserApi() {

    IUser user = UserFixturesUtils.getUser();

    return new UserApi(
        user.getUsername(),
        "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoic2FtcGxlczMiLCJleHAiOjU5NTkwOTQwMzE4LCJpYXQiOjE1OTA4NTM5MTh9.yJbuBMOoujDDWS0teH1orAhkulpCBsAiU5ydIr_Yfy_Y-9MpKKrpctS7WeFn1QzRXgVXGvsZon0Jr7ZnkrmAUg");
  }

  public static IFixtureFactory<IUserApi> getUserApiFactory() {
    return userApiFactory;
  }
}
