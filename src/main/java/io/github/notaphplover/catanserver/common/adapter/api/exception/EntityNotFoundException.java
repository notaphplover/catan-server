package io.github.notaphplover.catanserver.common.adapter.api.exception;

public class EntityNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 8014996096269593330L;

  public EntityNotFoundException(String message) {
    super(message);
  }
}
