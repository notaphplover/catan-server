package io.github.notaphplover.catanserver.user.adapter.api.controller;

import static org.mockito.Mockito.reset;

import io.github.notaphplover.catanserver.CatanServerApplication;
import io.github.notaphplover.catanserver.common.adapter.api.exception.EntityNotFoundException;
import io.github.notaphplover.catanserver.common.adapter.api.reqHandler.IReqHandler;
import io.github.notaphplover.catanserver.fixtures.user.adapter.api.model.UserApiFixturesUtils;
import io.github.notaphplover.catanserver.fixtures.user.adapter.api.request.PostUserRequestFixturesUtils;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.request.GetUserRequest;
import io.github.notaphplover.catanserver.user.adapter.api.request.PostUserRequest;
import java.util.Optional;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@DisplayName("UserController tests")
@SpringBootTest(classes = CatanServerApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
public class UserControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private IReqHandler<GetUserRequest, Optional<IUserApi>> getUserRequestHandler;

  @MockBean private IReqHandler<PostUserRequest, IUserApi> postUserReqHandler;

  @DisplayName("UserController.getMyUser")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class GetMyUser {

    @DisplayName("UserController.getMyUser, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {

      private MvcResult result;

      private String token;

      @BeforeAll
      public void beforeAll() throws Exception {

        Mockito.when(getUserRequestHandler.handle(ArgumentMatchers.any()))
            .thenReturn(Optional.of(UserApiFixturesUtils.getUserApiFactory().get()));

        token = UserApiFixturesUtils.getUserApiFactory().get().getToken();

        result =
            mockMvc
                .perform(
                    MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token))
                .andReturn();
      }

      @AfterAll
      public void afterAll() {
        reset(new IReqHandler[] {getUserRequestHandler});
      }

      @DisplayName("It must return its user")
      @Test
      public void itMustReturnItsUser() throws Exception {

        IUserApi userApi = UserApiFixturesUtils.getUserApiFactory().get();

        MockMvcResultMatchers.status().isOk().match(result);
        MockMvcResultMatchers.jsonPath("$.username").value(userApi.getUsername()).match(result);
        MockMvcResultMatchers.jsonPath("$.token").value(userApi.getToken()).match(result);
      }
    }

    @DisplayName("UserController.getMyUser, when called, and no user is found")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalledAndNoUserIsFound {

      private String errorMessage;

      private MvcResult result;

      private String token;

      @BeforeAll
      public void beforeAll() throws Exception {

        errorMessage = "Test when a user is not found";

        Mockito.when(getUserRequestHandler.handle(ArgumentMatchers.any()))
            .thenThrow(new EntityNotFoundException(errorMessage));

        token = UserApiFixturesUtils.getUserApiFactory().get().getToken();

        result =
            mockMvc
                .perform(
                    MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token))
                .andReturn();
      }

      @AfterAll
      public void afterAll() {
        reset(new IReqHandler[] {getUserRequestHandler});
      }

      @DisplayName("It must return a NOT_FOUND response")
      @Test
      public void itMustReturnANotFoundResponse() throws Exception {

        IUserApi userApi = UserApiFixturesUtils.getUserApiFactory().get();

        MockMvcResultMatchers.status().isNotFound().match(result);
        MockMvcResultMatchers.jsonPath("$.msg").value(errorMessage).match(result);
      }
    }
  }

  @DisplayName("UserController.newUser")
  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class NewUser {

    @DisplayName("UserController.newUser, when called")
    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenCalled {
      private MvcResult result;

      private String token;

      @BeforeAll
      public void beforeAll() throws Exception {

        Mockito.when(postUserReqHandler.handle(ArgumentMatchers.any()))
            .thenReturn(UserApiFixturesUtils.getUserApiFactory().get());

        token = UserApiFixturesUtils.getUserApiFactory().get().getToken();

        PostUserRequest postUserRequest =
            PostUserRequestFixturesUtils.postUserRequestFactory().get();

        String requestContent =
            String.format(
                "{ \"username\": \"%s\", \"password\": \"%s\" }",
                postUserRequest.getUsername(), postUserRequest.getPassword());

        result =
            mockMvc
                .perform(
                    MockMvcRequestBuilders.post("/users")
                        .header("Authorization", "Bearer " + token)
                        .content(requestContent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
      }

      @DisplayName("It must return its user")
      @Test
      public void itMustReturnItsUser() throws Exception {

        IUserApi userApi = UserApiFixturesUtils.getUserApiFactory().get();

        MockMvcResultMatchers.status().isOk().match(result);
        MockMvcResultMatchers.jsonPath("$.username").value(userApi.getUsername()).match(result);
        MockMvcResultMatchers.jsonPath("$.token").value(userApi.getToken()).match(result);
      }
    }
  }
}
