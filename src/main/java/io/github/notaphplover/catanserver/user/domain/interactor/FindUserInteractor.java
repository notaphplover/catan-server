package io.github.notaphplover.catanserver.user.domain.interactor;

import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.user.adapter.db.repository.UserRepositoryManager;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FindUserInteractor implements IInteractor<UserFindQuery, Optional<IUser>> {

  private UserRepositoryManager userRepositoryManager;

  public FindUserInteractor(UserRepositoryManager userRepositoryManager) {
    this.userRepositoryManager = userRepositoryManager;
  }

  @Override
  public Optional<IUser> interact(UserFindQuery input) {
    return userRepositoryManager.find(input);
  }
}
