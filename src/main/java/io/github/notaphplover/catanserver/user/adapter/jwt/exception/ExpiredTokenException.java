package io.github.notaphplover.catanserver.user.adapter.jwt.exception;

public class ExpiredTokenException extends RuntimeException {

  public ExpiredTokenException(Throwable cause) {
    super(cause);
  }
}
