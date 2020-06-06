package io.github.notaphplover.catanserver.user.port.db;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.security.domain.service.IPasswordEncoder;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class UserCreationQueryToUserCreationQueryDbPort
    implements IPort<UserCreationQuery, UserCreationQueryDb> {

  private IPasswordEncoder passwordEncoder;

  public UserCreationQueryToUserCreationQueryDbPort(IPasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserCreationQueryDb transform(UserCreationQuery input) {

    String username = input.getUsername();
    String passwordHash = passwordEncoder.encode(input.getPassword());

    return new UserCreationQueryDb(username, passwordHash);
  }
}
