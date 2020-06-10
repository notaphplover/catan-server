package io.github.notaphplover.catanserver.user.domain.interactor;

import io.github.notaphplover.catanserver.common.domain.interactor.IInteractor;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserFindQuery;
import io.github.notaphplover.catanserver.user.domain.repository.IUserSearchRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FindUserInteractor implements IInteractor<UserFindQuery, Optional<IUser>> {

  private IUserSearchRepository userSearchRepository;

  public FindUserInteractor(IUserSearchRepository userSearchRepository) {
    this.userSearchRepository = userSearchRepository;
  }

  @Override
  public Optional<IUser> interact(UserFindQuery input) {
    return userSearchRepository.findOne(input);
  }
}
