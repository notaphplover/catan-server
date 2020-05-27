package io.github.notaphplover.catanserver.user.adapter.db.repository;

import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserDb, Long> {}
