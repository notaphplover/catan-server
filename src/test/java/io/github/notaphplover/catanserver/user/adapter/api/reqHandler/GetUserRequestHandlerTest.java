package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.GetUserRequestFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.model.UserApiFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQueryFixturesUtils;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

@DisplayName("GetUserRequestHandler tests")
@TestInstance(Lifecycle.PER_CLASS)
public class GetUserRequestHandlerTest {

  private IInteractor<UserFindQuery, Optional<IUser>> findUserInteractor = null;

  private IPort<IUser, IUserApi> userToUserApiPort = null;

  private GetUserRequestHandler getUserRequestHandler = null;

  @BeforeAll
  @SuppressWarnings("unchecked")
  public void beforeAll() {
    findUserInteractor =
        (IInteractor<UserFindQuery, Optional<IUser>>) Mockito.mock(IInteractor.class);
    userToUserApiPort = (IPort<IUser, IUserApi>) Mockito.mock(IPort.class);

    getUserRequestHandler = new GetUserRequestHandler(findUserInteractor, userToUserApiPort);
  }

  @Nested
  @DisplayName("GetUserRequestHandler.handle")
  @TestInstance(Lifecycle.PER_CLASS)
  class Handle {

    @BeforeAll
    public void beforeAll() {
      when(findUserInteractor.interact(any()))
          .thenReturn(Optional.of(UserFixturesUtils.getUserFactory().get()));
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
        result =
            getUserRequestHandler.handle(GetUserRequestFixturesUtils.getUserRequestFactory().get());
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
  }
}
