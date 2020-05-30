package io.github.notaphplover.catanserver.user.port;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import org.springframework.stereotype.Service;

@Service
public class UserFindQueryToUserFindQueryDbPort implements IPort<UserFindQuery, UserFindQueryDb> {

  @Override
  public UserFindQueryDb transform(UserFindQuery input) {

    UserFindQueryDb queryDb = new UserFindQueryDb(input.getUsername());

    return queryDb;
  }
}
