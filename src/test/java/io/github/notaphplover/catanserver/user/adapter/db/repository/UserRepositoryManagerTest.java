package io.github.notaphplover.catanserver.user.adapter.db.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDbFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDbFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQueryFixturesUtils;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@DisplayName("UserRepositoryManager tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryManagerTest {

  private IJPAUserRepository innerRepository;

  private IPort<IUserCreationQuery, UserCreationQueryDb> userCreationQueryToUserCreationQueryDbPort;

  private IPort<UserDb, IUser> userDbToUserPort;

  private IPort<IUserFindQuery, UserFindQueryDb> userFindQueryToUserFindQueryDbPort;

  private UserRepositoryManager userRepositoryManager;

  @BeforeAll
  @SuppressWarnings("unchecked")
  public void beforeAll() {
    innerRepository = Mockito.mock(IJPAUserRepository.class);
    userCreationQueryToUserCreationQueryDbPort =
        (IPort<IUserCreationQuery, UserCreationQueryDb>) Mockito.mock(IPort.class);

    userDbToUserPort = (IPort<UserDb, IUser>) Mockito.mock(IPort.class);

    userFindQueryToUserFindQueryDbPort =
        (IPort<IUserFindQuery, UserFindQueryDb>) Mockito.mock(IPort.class);

    userRepositoryManager =
        new UserRepositoryManager(
            innerRepository,
            userCreationQueryToUserCreationQueryDbPort,
            userDbToUserPort,
            userFindQueryToUserFindQueryDbPort);
  }

  @DisplayName("UserRepositoryManager.createMany")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CreateMany {

    @BeforeAll
    public void beforeAll() {

      when(userCreationQueryToUserCreationQueryDbPort.transform(any()))
          .thenReturn(UserCreationQueryDbFixturesUtils.getUserCreationQueryDbFactory().get());

      List<UserDb> usersListFixture =
          Arrays.asList(new UserDb[] {UserDbFixturesUtils.getUserDbFactory().get()});
      when(innerRepository.saveAll(any())).thenReturn(usersListFixture);

      when(userDbToUserPort.transform(any())).thenReturn(UserFixturesUtils.getUserFactory().get());
    }

    @DisplayName("UserRepositoryManager.createMany, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      Object result;

      @BeforeAll
      public void beforeAll() {
        List<IUserCreationQuery> userCreationQueryListFixtures =
            Arrays.asList(
                new IUserCreationQuery[] {
                  UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get()
                });
        result = userRepositoryManager.createMany(userCreationQueryListFixtures);
      }

      @DisplayName(
          "It must call userCreationQueryToUserCreationQueryDbPort.transform with the query received")
      @Test
      public void itMustCallUserCreationQueryToUserCreationQueryDbPort() {
        verify(userCreationQueryToUserCreationQueryDbPort)
            .transform(eq(UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get()));
      }

      @DisplayName("It must call innerRepository.saveAll with the list of users to create")
      @Test
      public void itMustCallInnerRepository() {

        UserDb userDbFixture = UserDbFixturesUtils.getUserDbFactory().get();
        UserDb expectedUserDb = new UserDb();
        expectedUserDb.setPasswordHash(userDbFixture.getPasswordHash());
        expectedUserDb.setUsername(userDbFixture.getUsername());

        List<UserDb> expected = Arrays.asList(new UserDb[] {expectedUserDb});
        verify(innerRepository).saveAll(eq(expected));
      }

      @DisplayName("It muset call userDbToUserPort.transform with the users created")
      @Test
      public void itMustCallUserDbToUserPort() {
        verify(userDbToUserPort).transform(eq(UserDbFixturesUtils.getUserDbFactory().get()));
      }

      @DisplayName("It must return an array of users created")
      @Test
      public void itMustReturnAnArrayOfUsersCreated() {
        List<IUser> expected =
            Arrays.asList(new IUser[] {UserFixturesUtils.getUserFactory().get()});
        Assertions.assertEquals(expected, result);
      }
    }
  }
}
