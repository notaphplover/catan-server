package io.github.notaphplover.catanserver.user.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.notaphplover.catanserver.user.adapter.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    
}