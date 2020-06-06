package io.github.notaphplover.catanserver.user.domain.interactor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.user.adapter.db.repository.UserRepositoryManager;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQueryFixturesUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@DisplayName("CreateUserInteractor tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateUserInteractorTest {

  private UserRepositoryManager userRepositoryManager = null;

  private CreateUserInteractor createUserInteractor = null;

  @BeforeAll
  public void beforeAll() {

    userRepositoryManager = Mockito.mock(UserRepositoryManager.class);

    createUserInteractor = new CreateUserInteractor(userRepositoryManager);
  }

  @DisplayName("CreateUserInteractor.interact")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Interact {

    @BeforeAll
    public void beforeAll() {
      when(userRepositoryManager.create(any()))
          .thenReturn(UserFixturesUtils.getUserFactory().get());
    }

    @DisplayName("CreateUserInteractor.interact, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      private Object result = null;

      @BeforeAll
      public void beforeAll() {
        result =
            createUserInteractor.interact(
                UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get());
      }

      @DisplayName("It must return the user created")
      @Test
      public void itMustReturnAUser() {
        IUser expected = UserFixturesUtils.getUserFactory().get();

        assertEquals(expected, result);
      }
    }
  }
}
