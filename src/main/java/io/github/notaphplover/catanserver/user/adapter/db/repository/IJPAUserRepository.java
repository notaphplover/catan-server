package io.github.notaphplover.catanserver.user.adapter.db.repository;

import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IJPAUserRepository
    extends JpaRepository<UserDb, Long>, JpaSpecificationExecutor<UserDb> {}
