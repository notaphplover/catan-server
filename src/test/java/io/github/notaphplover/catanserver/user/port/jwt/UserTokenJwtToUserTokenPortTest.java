package io.github.notaphplover.catanserver.user.port.jwt;

import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserTokenFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwtFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.model.IUserToken;
import org.junit.jupiter.api.*;

@DisplayName("UserTokenJwtToUserTokenPort test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTokenJwtToUserTokenPortTest {

  private UserTokenJwtToUserTokenPort userTokenJwtToUserTokenPort = null;

  @BeforeAll
  public void beforeAll() {
    userTokenJwtToUserTokenPort = new UserTokenJwtToUserTokenPort();
  }

  @DisplayName("UserTokenJwtToUserTokenPort.transform")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Transform {

    @DisplayName("UserTokenJwtToUserTokenPort.transform, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      Object result = null;

      @BeforeAll
      public void beforeAll() {
        result =
            userTokenJwtToUserTokenPort.transform(
                UserTokenJwtFixturesUtils.getUserTokenJwtFactory().get());
      }

      @DisplayName("It must return an user token")
      @Test
      public void itMustReturnAUserToken() {
        IUserToken expected = UserTokenFixturesUtils.getUserTokenFactory().get();

        Assertions.assertEquals(expected, result);
      }
    }
  }
}
