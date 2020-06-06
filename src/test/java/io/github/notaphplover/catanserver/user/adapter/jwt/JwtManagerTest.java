package io.github.notaphplover.catanserver.user.adapter.jwt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.common.service.IDateService;
import io.github.notaphplover.catanserver.user.adapter.jwt.exception.ExpiredTokenException;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.IUserTokenJwt;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwt;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwtClaims;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

@DisplayName("JwtManagerTest tests")
@TestInstance(Lifecycle.PER_CLASS)
public class JwtManagerTest {

  private IDateService dateService = null;

  private String secret = null;

  private JwtManager jwtManager = null;

  @BeforeAll
  public void beforeAll() {
    dateService = Mockito.mock(IDateService.class);
    secret = "secret";
    jwtManager = new JwtManager(dateService, secret);
  }

  @Nested
  @DisplayName("JwtManager.generateToken")
  @TestInstance(Lifecycle.PER_CLASS)
  class GenerateToken {

    @BeforeAll
    public void beforeAll() {

      Date dateNow = new Date(1591457269064L);

      Date dateExpiresAt = new Date(2591457269064L);

      when(dateService.getCurrent()).thenReturn(dateNow);
      when(dateService.getWithOffset(any())).thenReturn(dateExpiresAt);
    }

    @AfterAll
    public void afterAll() {
      reset(dateService);
    }

    @Nested
    @DisplayName("JwtManager.generateToken, when called")
    @TestInstance(Lifecycle.PER_CLASS)
    class WhenCalled {

      private String expectedToken = null;
      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        expectedToken =
            "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoidXNlcm5hbWUiLCJleHAiOjI1OTE0NTcyNjksImlhdCI6MTU5MTQ1NzI2OX0.nba3v2euix03fuIoPKUkA7kd3itK_hHzpOFbLspuELaJ1i27xv9lPEVBZYqsRxezu7ASklfzYStuakuMmFBNTQ";
        result = jwtManager.generateToken(UserFixturesUtils.getUserFactory().get());
      }

      @DisplayName("It must return the expected token")
      @Test
      public void itMustReturnAToken() {
        assertEquals(expectedToken, result);
      }
    }
  }

  @DisplayName("JwtManager.validateAndGet")
  @Nested
  @TestInstance(Lifecycle.PER_CLASS)
  class ParseToken {

    @BeforeAll
    public void beforeAll() {

      Date dateNow = new Date(1591457269064L);

      when(dateService.getCurrent()).thenReturn(dateNow);
    }

    @AfterAll
    public void afterAll() {
      reset(dateService);
    }

    @DisplayName("JwtManager.validateAndGet, when called with a valid token")
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class WhenCalled {

      private IUserTokenJwt expectedUserTokenJwt = null;
      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        IUser user = UserFixturesUtils.getUserFactory().get();
        expectedUserTokenJwt =
            new UserTokenJwt(
                user.getUsername(), new UserTokenJwtClaims(user.getId(), user.getUsername()));

        String token =
            "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoidXNlcm5hbWUiLCJleHAiOjI1OTE0NTcyNjksImlhdCI6MTU5MTQ1NzI2OX0.nba3v2euix03fuIoPKUkA7kd3itK_hHzpOFbLspuELaJ1i27xv9lPEVBZYqsRxezu7ASklfzYStuakuMmFBNTQ";

        result = jwtManager.validateAndGet(token);
      }

      @DisplayName("It must return the expected user token")
      @Test
      public void itMustReturnAUserTokenJwt() {
        assertTrue(((Optional<?>) result).isPresent());
        assertEquals(expectedUserTokenJwt, ((Optional<?>) result).get());
      }
    }

    @DisplayName("JwtManager.validateAndGet, when called with an expired token")
    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    class WhenCalledWithExpiredToken {

      private IUserTokenJwt expectedUserTokenJwt = null;
      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        IUser user = UserFixturesUtils.getUserFactory().get();
        expectedUserTokenJwt =
            new UserTokenJwt(
                user.getUsername(), new UserTokenJwtClaims(user.getId(), user.getUsername()));

        String token =
            "eyJhbGciOiJIUzUxMiJ9.eyJodHRwczovL2dpdGh1Yi5jb20vbm90YXBocGxvdmVyL2NhdGFuLXNlcnZlci8iOnsiaWQiOjEsInVzZXJuYW1lIjoidXNlcm5hbWUifSwic3ViIjoidXNlcm5hbWUiLCJleHAiOjAsImlhdCI6MH0.xCdtZKeEj3kvGwuISn4A6hljigiXtKqCl7yGH2IYPaSjnXvSAwFG8vUxOQu9dRNW2FVT0Rzne97EZXrZvWOEHg";

        try {
          result = jwtManager.validateAndGet(token);
        } catch (Exception exception) {
          result = exception;
        }
      }

      @DisplayName("It must throw an ExpiredTokenException")
      @Test
      public void itMustThrowAnExpiredTokenException() {
        assertTrue(result instanceof ExpiredTokenException);
      }
    }
  }
}
