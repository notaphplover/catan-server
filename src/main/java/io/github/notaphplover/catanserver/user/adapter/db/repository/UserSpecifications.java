package io.github.notaphplover.catanserver.user.adapter.db.repository;

import org.springframework.data.jpa.domain.Specification;

import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb_;

public class UserSpecifications {
    public static Specification<UserDb> isUserWithUsername(String username) {
        return (root, query, cb) -> {
            return cb.equal(root.get(UserDb_.username), username);
        };
     }
}