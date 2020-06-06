package io.github.notaphplover.catanserver.user.port.api;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.api.model.IUserApi;
import io.github.notaphplover.catanserver.user.adapter.api.model.UserApi;
import io.github.notaphplover.catanserver.user.adapter.jwt.JwtManager;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserToUserApiPort implements IPort<IUser, IUserApi> {

  @Autowired private JwtManager jwtManager;

  @Override
  public IUserApi transform(IUser input) {

    String token = jwtManager.generateToken(input);
    UserApi userApi = new UserApi(input.getUsername(), token);

    return userApi;
  }
}
