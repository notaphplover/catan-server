package io.github.notaphplover.catanserver.user.adapter.repository;

import org.springframework.data.jpa.domain.Specification;

import io.github.notaphplover.catanserver.user.adapter.model.User;
import io.github.notaphplover.catanserver.user.adapter.model.User_;

public class UserSpecifications {
    public static Specification<User> isUserWithUsername(String username) {
        return (root, query, cb) -> {
            return cb.equal(root.get(User_.username), username);
        };
     }
}