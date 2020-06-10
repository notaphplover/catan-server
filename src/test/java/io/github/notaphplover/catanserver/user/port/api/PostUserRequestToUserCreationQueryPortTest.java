package io.github.notaphplover.catanserver.user.port.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequestFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQueryFixturesUtils;
import org.junit.jupiter.api.*;

@DisplayName("PostUserRequestToUserCreationQueryPort tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostUserRequestToUserCreationQueryPortTest {

  private PostUserRequestToUserCreationQueryPort postUserRequestToUserCreationQueryPort = null;

  @BeforeAll
  public void beforeAll() {
    postUserRequestToUserCreationQueryPort = new PostUserRequestToUserCreationQueryPort();
  }

  @DisplayName("PostUserRequestToUserCreationQueryPort.transform")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Transform {

    @DisplayName("PostUserRequestToUserCreationQueryPort.transform, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        result =
            postUserRequestToUserCreationQueryPort.transform(
                PostUserRequestFixturesUtils.postUserRequestFactory().get());
      }

      @DisplayName("It must return an UserCreationQuery")
      @Test
      public void itMustReturnACreationQuery() {
        IUserCreationQuery expected =
            UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get();

        assertEquals(expected, result);
      }
    }
  }
}
