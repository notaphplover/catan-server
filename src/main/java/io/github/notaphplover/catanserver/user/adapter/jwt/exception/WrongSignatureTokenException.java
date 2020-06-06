package io.github.notaphplover.catanserver.user.adapter.jwt.exception;

public class WrongSignatureTokenException extends RuntimeException {

  public WrongSignatureTokenException(Throwable cause) {
    super(cause);
  }
}
