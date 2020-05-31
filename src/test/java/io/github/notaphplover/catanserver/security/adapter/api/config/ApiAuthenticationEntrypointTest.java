package io.github.notaphplover.catanserver.security.adapter.api.config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.security.core.AuthenticationException;

@DisplayName("ApiAuthenticationEntrypoint tests")
public class ApiAuthenticationEntrypointTest {

  private ApiAuthenticationEntrypoint apiAuthenticationEntrypoint =
      new ApiAuthenticationEntrypoint();

  @Nested
  @DisplayName("ApiAuthenticationEntrypoint.commence")
  @TestInstance(Lifecycle.PER_CLASS)
  class Commence {

    @Nested
    @DisplayName("ApiAuthenticationEntrypoint.commence, when called")
    @TestInstance(Lifecycle.PER_CLASS)
    class WhenCalled {

      private HttpServletRequest requestMock = Mockito.mock(HttpServletRequest.class);
      private HttpServletResponse responseMock = Mockito.mock(HttpServletResponse.class);
      private AuthenticationException authExceptionMock =
          Mockito.mock(AuthenticationException.class);

      @BeforeAll
      public void beforeAll() throws IOException {
        apiAuthenticationEntrypoint.commence(requestMock, responseMock, authExceptionMock);
      }

      @DisplayName("it must call HttpServletResponse.sendError with an unauthorized status code")
      @Test
      public void itMustCallHttpServletResponse() throws IOException {
        verify(responseMock).sendError(eq(HttpServletResponse.SC_UNAUTHORIZED), any());
      }
    }
  }
}
