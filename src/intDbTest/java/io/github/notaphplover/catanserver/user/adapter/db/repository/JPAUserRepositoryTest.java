package io.github.notaphplover.catanserver.user.adapter.db.repository;

import io.github.notaphplover.catanserver.fixtures.user.adapter.db.model.UserDbFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.adapter.db.query.UserFindQueryDbFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("integration-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@DisplayName("JPAUserRepository tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JPAUserRepositoryTest {

  @Autowired private IJPAUserRepository jpaUserRepository;

  private UserSpecificationProvider userSpecificationProvider;

  @BeforeAll
  public void beforeAll() {
    userSpecificationProvider = new UserSpecificationProvider();
  }

  @DisplayName("JPAUserRepository.findAll")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class FindAll {

    @DisplayName("JPAUserRepository.findAll, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      Object result;

      @BeforeAll
      public void beforeAll() {
        jpaUserRepository.save(UserDbFixturesUtils.getUserDbFactory().get());
        result =
            jpaUserRepository.findAll(
                userSpecificationProvider.getCompliantWithSpec(
                    UserFindQueryDbFixturesUtils.getUserFindQueryDbFactory().get()));
      }

      @AfterAll
      public void afterAll() {
        jpaUserRepository.delete(UserDbFixturesUtils.getUserDbFactory().get());
      }

      @DisplayName("It must return the expected users")
      @Test
      public void itMustReturnTheExpectedUsers() {
        List<UserDb> expected = new LinkedList<>();
        expected.add(UserDbFixturesUtils.getUserDbFactory().get());

        Assertions.assertEquals(expected, result);
      }
    }
  }
}
