package io.github.notaphplover.catanserver.common.adapter.db.repository;

import java.util.Collection;
import java.util.Optional;

public interface ISearchRepository<D, O, M> {

  Collection<D> findMany(M query);

  Optional<D> findOne(O query);
}
