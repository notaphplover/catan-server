package io.github.notaphplover.catanserver.user.domain.query;

import java.util.Optional;

public interface IUserFindQuery {

  Optional<Long> getId();

  Optional<String> getUsername();
}
