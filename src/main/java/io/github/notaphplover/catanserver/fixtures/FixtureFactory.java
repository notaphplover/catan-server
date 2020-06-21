package io.github.notaphplover.catanserver.fixtures;

public abstract class FixtureFactory<T> implements IFixtureFactory<T> {

  private T entity;

  public FixtureFactory(T entity) {
    this.entity = entity;
  }

  public abstract T get();

  protected T getEntity() {
    return entity;
  }
}
