package io.github.notaphplover.catanserver.common.domain.repository;

public interface IRepository<T> {
    
    T save(T entity);
}