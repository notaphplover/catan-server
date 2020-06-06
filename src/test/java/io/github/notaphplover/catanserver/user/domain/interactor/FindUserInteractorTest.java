package io.github.notaphplover.catanserver.user.domain.interactor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.user.adapter.db.repository.UserRepositoryManager;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQueryFixturesUtils;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@DisplayName("FindUserInteractor tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindUserInteractorTest {

  private UserRepositoryManager userRepositoryManager = null;

  private FindUserInteractor findUserInteractor = null;

  @BeforeAll
  public void beforeAll() {

    userRepositoryManager = Mockito.mock(UserRepositoryManager.class);

    findUserInteractor = new FindUserInteractor(userRepositoryManager);
  }

  @DisplayName("FindUserInteractor.interact")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Interact {

    @BeforeAll
    public void beforeAll() {
      when(userRepositoryManager.find(any()))
          .thenReturn(Optional.of(UserFixturesUtils.getUserFactory().get()));
    }

    @DisplayName("FindUserInteractor.interact, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        result =
            findUserInteractor.interact(UserFindQueryFixturesUtils.getUserFindQueryFactory().get());
      }

      @DisplayName("It must return the user found")
      @Test
      public void itMustReturnAUserFound() {
        Optional<IUser> expected = Optional.of(UserFixturesUtils.getUserFactory().get());

        assertEquals(expected, result);
      }
    }
  }
}
