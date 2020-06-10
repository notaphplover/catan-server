package io.github.notaphplover.catanserver.common.adapter.db.repository;

import java.util.Collection;

public interface IUpdateRepository<D, O, M> {

  Collection<D> updateMany(M query);

  D updateOne(O query);
}
