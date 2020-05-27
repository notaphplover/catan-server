package io.github.notaphplover.catanserver.user.port;

import org.springframework.stereotype.Service;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.UserApi;
import io.github.notaphplover.catanserver.user.domain.model.IUser;

@Service
public class UserToUserApiPort implements IPort<IUser, UserApi> {

    @Override
    public UserApi transform(IUser input) {
        UserApi userApi = new UserApi();
        userApi.setUsername(input.getUsername());
        
        return userApi;
    }
    
}