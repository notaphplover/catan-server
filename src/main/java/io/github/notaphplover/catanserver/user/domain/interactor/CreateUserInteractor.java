package io.github.notaphplover.catanserver.user.domain.interactor;

import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import io.github.notaphplover.catanserver.user.domain.repository.IUserCreationRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserInteractor implements IInteractor<UserCreationQuery, IUser> {

  IUserCreationRepository creationRepository;

  public CreateUserInteractor(IUserCreationRepository creationRepository) {
    this.creationRepository = creationRepository;
  }

  @Override
  public IUser interact(UserCreationQuery input) {
    return creationRepository.createOne(input);
  }
}
