package io.github.notaphplover.catanserver.user.domain.interactor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQueryFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.repository.IUserSearchRepository;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@DisplayName("FindUserInteractor tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindUserInteractorTest {

  private IUserSearchRepository userSearchRepository = null;

  private FindUserInteractor findUserInteractor = null;

  @BeforeAll
  public void beforeAll() {

    userSearchRepository = Mockito.mock(IUserSearchRepository.class);

    findUserInteractor = new FindUserInteractor(userSearchRepository);
  }

  @DisplayName("FindUserInteractor.interact")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class Interact {

    @BeforeAll
    public void beforeAll() {
      when(userSearchRepository.findOne(any()))
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
