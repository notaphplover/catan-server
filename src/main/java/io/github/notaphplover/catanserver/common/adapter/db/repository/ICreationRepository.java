package io.github.notaphplover.catanserver.common.adapter.db.repository;

import java.util.Collection;

public interface ICreationRepository<D, O, M> {

  Collection<D> createMany(M query);

  D createOne(O query);
}
