package io.github.notaphplover.catanserver.user.port.db;

import io.github.notaphplover.catanserver.security.domain.service.IPasswordEncoder;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.fixtures.user.adapter.db.query.UserCreationQueryDbFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.domain.query.UserCreationQueryFixturesUtils;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@DisplayName("UserCreationQueryToUserCreationQueryDbPort tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserCreationQueryToUserCreationQueryDbPortTest {

  private IPasswordEncoder passwordEncoder;

  private UserCreationQueryToUserCreationQueryDbPort userCreationQueryToUserCreationQueryDbPort =
      null;

  @BeforeAll
  public void beforeAll() {
    passwordEncoder = Mockito.mock(IPasswordEncoder.class);

    userCreationQueryToUserCreationQueryDbPort =
        new UserCreationQueryToUserCreationQueryDbPort(passwordEncoder);
  }

  @DisplayName("UserCreationQueryToUserCreationQueryDbPort.transform")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Transform {

    @BeforeAll
    public void beforeAll() {
      Mockito.when(passwordEncoder.encode(ArgumentMatchers.any()))
          .thenReturn(
              UserCreationQueryDbFixturesUtils.getUserCreationQueryDbFactory()
                  .get()
                  .getPasswordHash());
    }

    @DisplayName("UserCreationQueryToUserCreationQueryDbPort.transform, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        result =
            userCreationQueryToUserCreationQueryDbPort.transform(
                UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get());
      }

      @DisplayName("It must return a UserCreationQueryDb")
      @Test
      public void itMustReturnAnUserCreationQueryDb() {
        UserCreationQueryDb expected =
            UserCreationQueryDbFixturesUtils.getUserCreationQueryDbFactory().get();

        Assertions.assertEquals(expected, result);
      }
    }
  }
}
