package io.github.notaphplover.catanserver.fixtures.user.adapter.api.model;

import io.github.notaphplover.catanserver.fixtures.IFixtureFactory;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.model.UserApi;
import io.github.notaphplover.catanserver.user.domain.model.IUser;

public class UserApiFixturesUtils {

  private static IFixtureFactory<IUserApi> userApiFactory =
      new UserApiFixturesFactory(getUserApi());

  public static IUserApi getUserApi() {

    IUser user = UserFixturesUtils.getUser();

    return new UserApi(
        user.getUsername(),
        "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoidXNlcm5hbWUiLCJleHAiOjU5NTkwOTQwMzE4LCJpYXQiOjE1OTA4NTM5MTh9.QxYv-b0XoD5yP_3CGUm3z7x04Vv0pUWHkMvcar54Zt9KQkDxVAY4PyMTrv_Q5o1r-mKSKmi8KGGZ5Y99cKi20w");
  }

  public static IFixtureFactory<IUserApi> getUserApiFactory() {
    return userApiFactory;
  }
}
