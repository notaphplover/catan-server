package io.github.notaphplover.catanserver.security.domain.service;

public interface IPasswordEncoder {
  public String encode(String password);

  public boolean validate(String password, String encoded);
}
