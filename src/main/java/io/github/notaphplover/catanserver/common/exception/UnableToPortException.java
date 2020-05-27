package io.github.notaphplover.catanserver.common.exception;

public class UnableToPortException extends RuntimeException {

  private static final long serialVersionUID = 5479253370158897792L;

  public UnableToPortException(Exception cause) {
    super(cause);
  }
}
