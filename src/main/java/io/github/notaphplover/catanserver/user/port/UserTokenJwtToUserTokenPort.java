package io.github.notaphplover.catanserver.user.port;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwt;
import io.github.notaphplover.catanserver.user.domain.model.IUserToken;
import io.github.notaphplover.catanserver.user.domain.model.UserToken;
import org.springframework.stereotype.Service;

@Service
public class UserTokenJwtToUserTokenPort implements IPort<UserTokenJwt, IUserToken> {

  @Override
  public IUserToken transform(UserTokenJwt input) {
    return new UserToken(input.getClaims().getId(), input.getClaims().getUsername());
  }
}
