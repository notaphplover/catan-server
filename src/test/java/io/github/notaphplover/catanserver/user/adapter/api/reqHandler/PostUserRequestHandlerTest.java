package io.github.notaphplover.catanserver.user.adapter.api.reqHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.fixtures.user.adapter.api.model.UserApiFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.adapter.api.request.PostUserRequestFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.domain.query.UserCreationQueryFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

@DisplayName("PostUserRequestHandler tests")
@TestInstance(Lifecycle.PER_CLASS)
public class PostUserRequestHandlerTest {

  private IInteractor<IUserCreationQuery, IUser> createUserInteractor = null;

  private IPort<PostUserRequest, IUserCreationQuery> postUserRequestToUserCreationQueryPort;

  private IPort<IUser, IUserApi> userToUserApiPort = null;

  private PostUserReqHandler postUserRequestHandler = null;

  @BeforeAll
  @SuppressWarnings("unchecked")
  public void beforeAll() {
    createUserInteractor = (IInteractor<IUserCreationQuery, IUser>) Mockito.mock(IInteractor.class);

    postUserRequestToUserCreationQueryPort =
        (IPort<PostUserRequest, IUserCreationQuery>) Mockito.mock(IPort.class);

    userToUserApiPort = (IPort<IUser, IUserApi>) Mockito.mock(IPort.class);

    postUserRequestHandler =
        new PostUserReqHandler(
            createUserInteractor, postUserRequestToUserCreationQueryPort, userToUserApiPort);
  }

  @Nested
  @DisplayName("PostUserRequestHandler.handle")
  @TestInstance(Lifecycle.PER_CLASS)
  class Handle {

    @BeforeAll
    public void beforeAll() {
      when(createUserInteractor.interact(any()))
          .thenReturn(UserFixturesUtils.getUserFactory().get());
      when(postUserRequestToUserCreationQueryPort.transform(any()))
          .thenReturn(UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get());
      when(userToUserApiPort.transform(any()))
          .thenReturn(UserApiFixturesUtils.getUserApiFactory().get());
    }

    @Nested
    @DisplayName("PostUserRequestHandler.handle, when called")
    @TestInstance(Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        result =
            postUserRequestHandler.handle(
                PostUserRequestFixturesUtils.postUserRequestFactory().get());
      }

      @DisplayName("It must call CreateUserInteractor.interact with the CreateUserQuery received")
      @Test()
      public void itMustCallCreateUserInteractor() {
        verify(createUserInteractor)
            .interact(eq(UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get()));
      }

      @DisplayName(
          "It must call PostUserRequestToUserCreationQueryPort.transform with the PostUserRequest received")
      @Test()
      public void itMustCallPostUserRequestToUserCreationQueryPort() {
        verify(postUserRequestToUserCreationQueryPort)
            .transform(eq(PostUserRequestFixturesUtils.postUserRequestFactory().get()));
      }

      @DisplayName("It must call UserToUserApiPort.transform with the IUser found")
      @Test
      public void itMustCallUserToApiPort() {
        verify(userToUserApiPort).transform(eq(UserFixturesUtils.getUserFactory().get()));
      }

      @DisplayName("It must return the expected user")
      @Test
      public void itMustReturnAUser() {
        assertEquals(UserApiFixturesUtils.getUserApiFactory().get(), result);
      }
    }
  }
}
