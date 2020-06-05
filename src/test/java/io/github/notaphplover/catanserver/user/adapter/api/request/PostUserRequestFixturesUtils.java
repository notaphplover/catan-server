package io.github.notaphplover.catanserver.user.adapter.api.request;

import io.github.notaphplover.catanserver.common.IFixtureFactory;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;

public class PostUserRequestFixturesUtils {

  private static IFixtureFactory<PostUserRequest> postUserRequestFactory =
      new PostUserRequestFixturesFactory(postUserRequest());

  public static PostUserRequest postUserRequest() {

    IUser user = UserFixturesUtils.getUser();

    PostUserRequest postUserRequest = new PostUserRequest();
    postUserRequest.setPassword(
        "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoic2FtcGxlczMiLCJleHAiOjU5NTkwOTQwMzE4LCJpYXQiOjE1OTA4NTM5MTh9.yJbuBMOoujDDWS0teH1orAhkulpCBsAiU5ydIr_Yfy_Y-9MpKKrpctS7WeFn1QzRXgVXGvsZon0Jr7ZnkrmAUg");
    postUserRequest.setUsername(user.getUsername());

    return postUserRequest;
  }

  public static IFixtureFactory<PostUserRequest> postUserRequestFactory() {
    return postUserRequestFactory;
  }
}
