package io.github.notaphplover.catanserver.user.port;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.model.UserApi;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import org.springframework.stereotype.Service;

@Service
public class UserToUserApiPort implements IPort<IUser, IUserApi> {

  @Override
  public IUserApi transform(IUser input) {
    UserApi userApi = new UserApi(input.getUsername());

    return userApi;
  }
}
