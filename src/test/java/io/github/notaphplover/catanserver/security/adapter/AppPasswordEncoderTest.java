package io.github.notaphplover.catanserver.security.adapter;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

@DisplayName("ApiAuthenticationEntrypoint tests")
@TestInstance(Lifecycle.PER_CLASS)
public class AppPasswordEncoderTest {
    
    PasswordEncoder passwordEncoder = null;

    AppPasswordEncoder appPasswordEncoder = null;

    @BeforeAll
    public void beforeAll() {
        passwordEncoder = Mockito.mock(PasswordEncoder.class);

        appPasswordEncoder = new AppPasswordEncoder(passwordEncoder);
    }

    @Nested
    @DisplayName("AppPasswordEncoder.encode")
    @TestInstance(Lifecycle.PER_CLASS)
    class Encode {

        private String passwordEncoderEncodeReturnValue = "passwordEncoderEncode.encode return value"; 

        @BeforeAll
        public void beforeAll() {
            when(passwordEncoder.encode(anyString())).thenReturn(passwordEncoderEncodeReturnValue);
        }

        @Nested
        @DisplayName("AppPasswordEncoder.encode, when called")
        @TestInstance(Lifecycle.PER_CLASS)
        class WhenCalled {

            private String passwordParam = null;
            private Object result = null;

            @BeforeAll
            public void beforeAll() {
                passwordParam = "password";
                result = appPasswordEncoder.encode(passwordParam);
            }

            @DisplayName("It must call PasswordEncoder.encode with the password received")
            @Test
            public void itMustCallPasswordEncoder() {
                verify(passwordEncoder).encode(eq(passwordParam));
            }

            @DisplayName("It must call return the encoded value")
            @Test
            public void itMustReturnTheEncodedValue() {
                assertSame(passwordEncoderEncodeReturnValue, result);
            }
        }
    }
}
