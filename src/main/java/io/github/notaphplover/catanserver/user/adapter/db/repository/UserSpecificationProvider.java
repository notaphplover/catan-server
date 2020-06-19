package io.github.notaphplover.catanserver.user.adapter.db.repository;

import io.github.notaphplover.catanserver.common.adapter.db.repository.ISpecificationProvider;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb_;
import io.github.notaphplover.catanserver.user.adapter.db.query.UserFindQueryDb;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecificationProvider
    implements ISpecificationProvider<Specification<UserDb>, UserFindQueryDb> {

  @Override
  public Specification<UserDb> getCompliantWithSpec(UserFindQueryDb queryDb) {
    return (root, query, cb) -> {
      List<Predicate> predicatesList = new LinkedList<>();

      if (queryDb.getId().isPresent()) {
        predicatesList.add(cb.equal(root.get(UserDb_.id), queryDb.getId().get()));
      }

      if (queryDb.getUsername().isPresent()) {
        predicatesList.add(cb.equal(root.get(UserDb_.username), queryDb.getUsername().get()));
      }

      Predicate[] predicates = new Predicate[predicatesList.size()];
      predicatesList.toArray(predicates);

      return cb.and(predicates);
    };
  }
}
