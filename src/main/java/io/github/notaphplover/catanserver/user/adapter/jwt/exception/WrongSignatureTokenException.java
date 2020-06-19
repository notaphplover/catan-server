package io.github.notaphplover.catanserver.user.adapter.jwt.exception;

public class WrongSignatureTokenException extends RuntimeException {

  private static final long serialVersionUID = -2659113709708157663L;

  public WrongSignatureTokenException(Throwable cause) {
    super(cause);
  }
}
