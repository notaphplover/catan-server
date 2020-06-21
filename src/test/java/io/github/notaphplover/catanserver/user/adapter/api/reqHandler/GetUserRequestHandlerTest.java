package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import io.github.notaphplover.catanserver.common.adapter.api.exception.EntityNotFoundException;
import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.fixtures.user.adapter.api.model.UserApiFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.adapter.api.request.GetUserRequestFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.domain.query.UserFindQueryFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

@DisplayName("GetUserRequestHandler tests")
@TestInstance(Lifecycle.PER_CLASS)
public class GetUserRequestHandlerTest {

  private IInteractor<IUserFindQuery, Optional<IUser>> findUserInteractor = null;

  private IPort<IUser, IUserApi> userToUserApiPort = null;

  private GetUserRequestHandler getUserRequestHandler = null;

  @BeforeAll
  @SuppressWarnings("unchecked")
  public void beforeAll() {
    findUserInteractor =
        (IInteractor<IUserFindQuery, Optional<IUser>>) Mockito.mock(IInteractor.class);
    userToUserApiPort = (IPort<IUser, IUserApi>) Mockito.mock(IPort.class);

    getUserRequestHandler = new GetUserRequestHandler(findUserInteractor, userToUserApiPort);
  }

  @Nested
  @DisplayName("GetUserRequestHandler.handle")
  @TestInstance(Lifecycle.PER_CLASS)
  class Handle {

    @BeforeAll
    public void beforeAll() {
      when(userToUserApiPort.transform(any()))
          .thenReturn(UserApiFixturesUtils.getUserApiFactory().get());
    }

    @Nested
    @DisplayName("GetUserRequestHandler.handle, when called")
    @TestInstance(Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {

        when(findUserInteractor.interact(any()))
            .thenReturn(Optional.of(UserFixturesUtils.getUserFactory().get()));

        result =
            getUserRequestHandler.handle(GetUserRequestFixturesUtils.getUserRequestFactory().get());
      }

      @AfterAll
      public void afterAll() {
        reset(new IInteractor[] {findUserInteractor});
      }

      @DisplayName("It must call FindUserInteractor.interact with the FindUserQuery received")
      @Test()
      public void itMustCallFindUserInteractor() {
        verify(findUserInteractor)
            .interact(eq(UserFindQueryFixturesUtils.getUserFindQueryFactory().get()));
      }

      @DisplayName("It must call UserToUserApiPort.transform with the IUser found")
      @Test
      public void itMustCallUserToApiPort() {
        verify(userToUserApiPort).transform(eq(UserFixturesUtils.getUserFactory().get()));
      }

      @DisplayName("It must return the expected user")
      @Test
      public void itMustReturnAUser() {
        Optional<IUserApi> expected = Optional.of(UserApiFixturesUtils.getUserApiFactory().get());

        assertEquals(expected, result);
      }
    }

    @Nested
    @DisplayName("GetUserRequestHandler.handle, when called and no user is found")
    @TestInstance(Lifecycle.PER_CLASS)
    class WhenCalledAndNoUserIsFound {

      Object result;

      @BeforeAll
      public void beforeAll() {

        when(findUserInteractor.interact(any())).thenReturn(Optional.empty());

        try {
          result =
              getUserRequestHandler.handle(
                  GetUserRequestFixturesUtils.getUserRequestFactory().get());
        } catch (EntityNotFoundException e) {
          result = e;
        }
      }

      @AfterAll
      public void afterAll() {
        reset(new IInteractor[] {findUserInteractor});
      }

      @DisplayName("It must throw an EntityNotFoundException")
      @Test
      public void itMustThrowAnEntityNotFoundException() {
        assertTrue(result instanceof EntityNotFoundException);
      }
    }
  }
}
