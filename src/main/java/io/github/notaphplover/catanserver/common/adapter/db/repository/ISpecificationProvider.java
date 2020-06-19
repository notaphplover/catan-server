package io.github.notaphplover.catanserver.common.adapter.db.repository;

import org.springframework.data.jpa.domain.Specification;

public interface ISpecificationProvider<S extends Specification<?>, Q> {
  S getCompliantWithSpec(Q query);
}
