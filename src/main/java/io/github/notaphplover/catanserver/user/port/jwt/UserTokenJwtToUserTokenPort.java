package io.github.notaphplover.catanserver.user.port.jwt;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.IUserTokenJwt;
import io.github.notaphplover.catanserver.user.domain.model.IUserToken;
import io.github.notaphplover.catanserver.user.domain.model.UserToken;
import org.springframework.stereotype.Service;

@Service
public class UserTokenJwtToUserTokenPort implements IPort<IUserTokenJwt, IUserToken> {

  @Override
  public IUserToken transform(IUserTokenJwt input) {
    return new UserToken(input.getClaims().getId(), input.getClaims().getUsername());
  }
}
