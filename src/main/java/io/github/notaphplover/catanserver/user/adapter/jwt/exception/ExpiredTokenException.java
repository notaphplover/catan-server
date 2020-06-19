package io.github.notaphplover.catanserver.user.adapter.jwt.exception;

public class ExpiredTokenException extends RuntimeException {

  private static final long serialVersionUID = 3538840686572908150L;

  public ExpiredTokenException(Throwable cause) {
    super(cause);
  }
}
