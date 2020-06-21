package io.github.notaphplover.catanserver.user.port.db;

import io.github.notaphplover.catanserver.fixtures.user.adapter.db.model.UserDbFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import org.junit.jupiter.api.*;

@DisplayName("UserFindQueryToUserFindQueryDbPort test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDbToUserPortTest {

  private UserDbToUserPort userDbToUserPort;

  @BeforeAll
  public void beforeAll() {
    userDbToUserPort = new UserDbToUserPort();
  }

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Transform {

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      Object result = null;

      @BeforeAll
      public void beforeAll() {
        result = userDbToUserPort.transform(UserDbFixturesUtils.getUserDbFactory().get());
      }

      @DisplayName("It must return an IUser based on the UserDb provided")
      @Test
      public void itMustReturnAnUser() {
        IUser expected = UserFixturesUtils.getUserFactory().get();
        Assertions.assertEquals(expected, result);
      }
    }
  }
}
