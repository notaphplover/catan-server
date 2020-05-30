package io.github.notaphplover.catanserver.user.adapter.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GetUserRequest {
    
    @Size(min = 6, max = 128)
    @NotBlank
    @NotNull
    private String username;
    
    @Size(min = 8, max = 128)
    @NotBlank
    @NotNull
    private String password;

    public GetUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}