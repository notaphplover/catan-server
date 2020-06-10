package io.github.notaphplover.catanserver.user.domain.repository;

import io.github.notaphplover.catanserver.common.domain.repository.ICreationRepository;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.UserCreationQuery;
import java.util.Collection;

public interface IUserCreationRepository
    extends ICreationRepository<IUser, UserCreationQuery, Collection<UserCreationQuery>> {}
