package io.github.notaphplover.catanserver.user.port.db;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.security.domain.service.IPasswordEncoder;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserCreationQueryDb;
import io.github.notaphplover.catanserver.user.domain.query.IUserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class UserCreationQueryToUserCreationQueryDbPort
    implements IPort<IUserCreationQuery, UserCreationQueryDb> {

  private IPasswordEncoder passwordEncoder;

  public UserCreationQueryToUserCreationQueryDbPort(IPasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserCreationQueryDb transform(IUserCreationQuery input) {

    String username = input.getUsername();
    String passwordHash = passwordEncoder.encode(input.getPassword());

    return new UserCreationQueryDb(username, passwordHash);
  }
}
