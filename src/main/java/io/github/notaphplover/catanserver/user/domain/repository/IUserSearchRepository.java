package io.github.notaphplover.catanserver.user.domain.repository;

import io.github.notaphplover.catanserver.common.domain.repository.ISearchRepository;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.github.notaphplover.catanserver.user.domain.query.IUserFindQuery;

public interface IUserSearchRepository
    extends ISearchRepository<IUser, IUserFindQuery, IUserFindQuery> {}
