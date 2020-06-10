package io.github.notaphplover.catanserver.user.port.db;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;
import org.springframework.stereotype.Service;

@Service
public class UserFindQueryToUserFindQueryDbPort implements IPort<IUserFindQuery, UserFindQueryDb> {

  @Override
  public UserFindQueryDb transform(IUserFindQuery input) {

    UserFindQueryDb queryDb = new UserFindQueryDb(input.getId(), input.getUsername());

    return queryDb;
  }
}
