package io.github.notaphplover.catanserver.user.port.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.jwt.JwtManager;
import io.github.notaphplover.catanserver.user.adapter.model.UserApiFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@DisplayName("UserToUserApiPort tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserToUserApiPortTest {

  private JwtManager jwtManager = null;

  private UserToUserApiPort userToUserApiPort = null;

  @BeforeAll
  public void beforeAll() {

    jwtManager = Mockito.mock(JwtManager.class);

    userToUserApiPort = new UserToUserApiPort(jwtManager);
  }

  @DisplayName("UserToUserApiPort.transform")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Transform {

    @BeforeAll
    public void beforeAll() {
      when(jwtManager.generateToken(any()))
          .thenReturn(UserApiFixturesUtils.getUserApiFactory().get().getToken());
    }

    @DisplayName("UserToUserApiPort.transform, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        result = userToUserApiPort.transform(UserFixturesUtils.getUserFactory().get());
      }

      @DisplayName("It must return an UserCreationQuery")
      @Test
      public void itMustReturnACreationQuery() {
        IUserApi expected = UserApiFixturesUtils.getUserApiFactory().get();

        assertEquals(expected, result);
      }
    }
  }
}
