package io.github.notaphplover.catanserver.user.adapter.db.model;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;

public class UserDbFixturesUtils {

  private static IFixtureFactory<UserDb> userDbFactory = new UserDbFixturesFactory(getUserDb());

  public static UserDb getUserDb() {
    IUser user = UserFixturesUtils.getUserFactory().get();

    UserDb userDb = new UserDb(user.getId());
    userDb.setUsername(user.getUsername());
    userDb.setPasswordHash(
        "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoic2FtcGxlczMiLCJleHAiOjU5NTkwOTQwMzE4LCJpYXQiOjE1OTA4NTM5MTh9.yJbuBMOoujDDWS0teH1orAhkulpCBsAiU5ydIr_Yfy_Y-9MpKKrpctS7WeFn1QzRXgVXGvsZon0Jr7ZnkrmAUg");

    return userDb;
  }

  public static IFixtureFactory<UserDb> getUserDbFactory() {
    return userDbFactory;
  }
}
