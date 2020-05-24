package io.github.notaphplover.catanserver.user.adapter.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.notaphplover.catanserver.user.adapter.db.model.UserDb;

public interface IUserRepository extends JpaRepository<UserDb, Long> {
    
}