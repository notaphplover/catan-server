package io.github.notaphplover.catanserver.user.port.db;

import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDbFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQueryFixturesUtils;
import org.junit.jupiter.api.*;

@DisplayName("UserFindQueryToUserFindQueryDbPort test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserFindQueryToUserFindQueryDbPortTest {

  private UserFindQueryToUserFindQueryDbPort userFindQueryToUserFindQueryDbPort;

  @BeforeAll
  public void beforeAll() {
    userFindQueryToUserFindQueryDbPort = new UserFindQueryToUserFindQueryDbPort();
  }

  @DisplayName("UserFindQueryToUserFindQueryDbPort.transform")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Transform {

    @DisplayName("UserFindQueryToUserFindQueryDbPort.transform, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        result =
            userFindQueryToUserFindQueryDbPort.transform(
                UserFindQueryFixturesUtils.getUserFindQueryFactory().get());
      }

      @DisplayName("It must return an UserCreationQueryDb")
      @Test
      public void itMustReturnAnUserCreationQueryDb() {
        UserFindQueryDb expected = UserFindQueryDbFixturesUtils.getUserFindQueryDbFactory().get();

        Assertions.assertEquals(expected, result);
      }
    }
  }
}
