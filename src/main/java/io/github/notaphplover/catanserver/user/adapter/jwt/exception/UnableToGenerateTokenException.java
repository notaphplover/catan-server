package io.github.notaphplover.catanserver.user.adapter.jwt.exception;

public class UnableToGenerateTokenException extends RuntimeException {

  private static final long serialVersionUID = 5436532564417762087L;

  public UnableToGenerateTokenException(String msg) {
    super(msg);
  }
}
