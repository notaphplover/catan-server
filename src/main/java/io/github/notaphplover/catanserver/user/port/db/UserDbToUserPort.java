package io.github.notaphplover.catanserver.user.port.db;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserDbToUserPort implements IPort<UserDb, IUser> {

  @Override
  public IUser transform(UserDb input) {
    IUser user = new User(input.getId());
    user.setPasswordHash(input.getPasswordHash());
    user.setUsername(input.getUsername());

    return user;
  }
}
