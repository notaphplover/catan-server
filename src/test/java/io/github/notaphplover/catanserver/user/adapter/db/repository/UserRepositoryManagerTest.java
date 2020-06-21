package io.github.notaphplover.catanserver.user.adapter.db.repository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import io.github.notaphplover.catanserver.common.adapter.db.repository.ISpecificationProvider;
import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.fixtures.user.adapter.db.query.UserCreationQueryDbFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.adapter.db.query.UserFindQueryDbFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.fixtures.user.adapter.db.model.UserDbFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.db.query.*;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.fixtures.user.domain.model.UserFixturesUtils;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import io.github.notaphplover.catanserver.fixtures.user.domain.query.UserCreationQueryFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.domain.query.UserFindQueryFixturesUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.data.jpa.domain.Specification;

@DisplayName("UserRepositoryManager tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryManagerTest {

  private IJPAUserRepository innerRepository;

  private IPort<IUserCreationQuery, UserCreationQueryDb> userCreationQueryToUserCreationQueryDbPort;

  private IPort<UserDb, IUser> userDbToUserPort;

  private IPort<IUserFindQuery, UserFindQueryDb> userFindQueryToUserFindQueryDbPort;

  private ISpecificationProvider<Specification<UserDb>, UserFindQueryDb>
      userDbSpecificationProvider;

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

    userDbSpecificationProvider =
        (ISpecificationProvider<Specification<UserDb>, UserFindQueryDb>)
            Mockito.mock(ISpecificationProvider.class);

    userRepositoryManager =
        new UserRepositoryManager(
            innerRepository,
            userCreationQueryToUserCreationQueryDbPort,
            userDbToUserPort,
            userFindQueryToUserFindQueryDbPort,
            userDbSpecificationProvider);
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

    @AfterAll
    public void afterAll() {
      reset(new IPort[] {userCreationQueryToUserCreationQueryDbPort});
      reset(new IJPAUserRepository[] {innerRepository});
      reset(new IPort[] {userDbToUserPort});
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

  @DisplayName("UserRepositoryManager.createOne")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CreateOne {

    @BeforeAll
    public void beforeAll() {

      when(userCreationQueryToUserCreationQueryDbPort.transform(any()))
          .thenReturn(UserCreationQueryDbFixturesUtils.getUserCreationQueryDbFactory().get());

      when(innerRepository.save(any())).thenReturn(UserDbFixturesUtils.getUserDbFactory().get());

      when(userDbToUserPort.transform(any())).thenReturn(UserFixturesUtils.getUserFactory().get());
    }

    @AfterAll
    public void afterAll() {
      reset(new IPort[] {userCreationQueryToUserCreationQueryDbPort});
      reset(new IJPAUserRepository[] {innerRepository});
      reset(new IPort[] {userDbToUserPort});
    }

    @DisplayName("UserRepositoryManager.createOne, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      Object result;

      @BeforeAll
      public void beforeAll() {
        result =
            userRepositoryManager.createOne(
                UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get());
      }

      @DisplayName(
          "It must call userCreationQueryToUserCreationQueryDbPort.transform with the query received")
      @Test
      public void itMustCallUserCreationQueryToUserCreationQueryDbPort() {
        verify(userCreationQueryToUserCreationQueryDbPort)
            .transform(eq(UserCreationQueryFixturesUtils.getUserCreationQueryFactory().get()));
      }

      @DisplayName("It must call innerRepository.save with the user to create")
      @Test
      public void itMustCallInnerRepository() {

        UserDb userDbFixture = UserDbFixturesUtils.getUserDbFactory().get();
        UserDb expected = new UserDb();
        expected.setPasswordHash(userDbFixture.getPasswordHash());
        expected.setUsername(userDbFixture.getUsername());

        verify(innerRepository).save(eq(expected));
      }

      @DisplayName("It must call userDbToUserPort.transform with the users created")
      @Test
      public void itMustCallUserDbToUserPort() {
        verify(userDbToUserPort).transform(eq(UserDbFixturesUtils.getUserDbFactory().get()));
      }

      @DisplayName("It must return an array of users created")
      @Test
      public void itMustReturnAnArrayOfUsersCreated() {
        IUser expected = UserFixturesUtils.getUserFactory().get();

        Assertions.assertEquals(expected, result);
      }
    }
  }

  @DisplayName("UserRepositoryManager.findMany")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class FindMany {

    private Specification<UserDb> userDbSpecificationFixture;

    @BeforeAll
    @SuppressWarnings("unchecked")
    public void beforeAll() {

      userDbSpecificationFixture = Mockito.mock(Specification.class);

      when(userFindQueryToUserFindQueryDbPort.transform(any()))
          .thenReturn(UserFindQueryDbFixturesUtils.getUserFindQueryDbFactory().get());

      List<UserDb> usersListFixture =
          Arrays.asList(new UserDb[] {UserDbFixturesUtils.getUserDbFactory().get()});

      when(innerRepository.findAll((Specification<UserDb>) any())).thenReturn(usersListFixture);

      when(userDbToUserPort.transform(any())).thenReturn(UserFixturesUtils.getUserFactory().get());

      when(userDbSpecificationProvider.getCompliantWithSpec(any()))
          .thenReturn(userDbSpecificationFixture);
    }

    @AfterAll
    public void afterAll() {
      reset(new IPort[] {userFindQueryToUserFindQueryDbPort});
      reset(new IJPAUserRepository[] {innerRepository});
      reset(new IPort[] {userDbToUserPort});
      reset(new ISpecificationProvider[] {userDbSpecificationProvider});
    }

    @DisplayName("UserRepositoryManager.findMany, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      Object result;

      @BeforeAll
      public void beforeAll() {
        result =
            userRepositoryManager.findMany(
                UserFindQueryFixturesUtils.getUserFindQueryFactory().get());
      }

      @DisplayName("It must call innerRepository.findAll with the spec of users to find")
      @Test
      public void itMustCallInnerRepository() {

        verify(innerRepository).findAll(eq(userDbSpecificationFixture));
      }

      @DisplayName("It must call userDbToUserPort.transform with the users found")
      @Test
      public void itMustCallUserDbToUserPort() {
        verify(userDbToUserPort).transform(eq(UserDbFixturesUtils.getUserDbFactory().get()));
      }

      @DisplayName("It must return an array of users found")
      @Test
      public void itMustReturnAnArrayOfUsersFound() {
        List<IUser> expected =
            Arrays.asList(new IUser[] {UserFixturesUtils.getUserFactory().get()});
        Assertions.assertEquals(expected, result);
      }
    }
  }

  @DisplayName("UserRepositoryManager.findOne")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class FindOne {

    private Specification<UserDb> userDbSpecificationFixture;

    @BeforeAll
    @SuppressWarnings("unchecked")
    public void beforeAll() {

      userDbSpecificationFixture = Mockito.mock(Specification.class);

      when(userFindQueryToUserFindQueryDbPort.transform(any()))
          .thenReturn(UserFindQueryDbFixturesUtils.getUserFindQueryDbFactory().get());

      when(innerRepository.findOne((Specification<UserDb>) any()))
          .thenReturn(Optional.of(UserDbFixturesUtils.getUserDbFactory().get()));

      when(userDbToUserPort.transform(any())).thenReturn(UserFixturesUtils.getUserFactory().get());

      when(userDbSpecificationProvider.getCompliantWithSpec(any()))
          .thenReturn(userDbSpecificationFixture);
    }

    @AfterAll
    public void afterAll() {
      reset(new IPort[] {userFindQueryToUserFindQueryDbPort});
      reset(new IJPAUserRepository[] {innerRepository});
      reset(new IPort[] {userDbToUserPort});
      reset(new ISpecificationProvider[] {userDbSpecificationProvider});
    }

    @DisplayName("UserRepositoryManager.findOne, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      Object result;

      @BeforeAll
      public void beforeAll() {
        result =
            userRepositoryManager.findOne(
                UserFindQueryFixturesUtils.getUserFindQueryFactory().get());
      }

      @DisplayName("It must call innerRepository.findOne with the spec of users to find")
      @Test
      public void itMustCallInnerRepository() {

        verify(innerRepository).findOne(eq(userDbSpecificationFixture));
      }

      @DisplayName("It must call userDbToUserPort.transform with the users found")
      @Test
      public void itMustCallUserDbToUserPort() {
        verify(userDbToUserPort).transform(eq(UserDbFixturesUtils.getUserDbFactory().get()));
      }

      @DisplayName("It must return an user found")
      @Test
      public void itMustReturnAnUserFound() {
        Optional<IUser> expected = Optional.of(UserFixturesUtils.getUserFactory().get());

        Assertions.assertEquals(expected, result);
      }
    }
  }
}
