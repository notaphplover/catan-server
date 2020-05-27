package io.github.notaphplover.catanserver.user.domain.interactor;

import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.user.adapter.db.repository.UserRepositoryManager;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import org.springframework.stereotype.Service;

@Service
public class CreateUserInteractor implements IInteractor<UserCreationQuery, IUser> {

  UserRepositoryManager userRepositoryManager;

  public CreateUserInteractor(UserRepositoryManager userRepositoryManager) {
    this.userRepositoryManager = userRepositoryManager;
  }

  @Override
  public IUser interact(UserCreationQuery input) {
    return userRepositoryManager.create(input);
  }
}
