package io.github.notaphplover.catanserver.security.adapter;

import io.github.notaphplover.catanserver.security.domain.service.IPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppPasswordEncoder implements IPasswordEncoder {

  private PasswordEncoder innerPasswordEncoder;

  public AppPasswordEncoder(PasswordEncoder innerPasswordEncoder) {
    this.innerPasswordEncoder = innerPasswordEncoder;
  }

  @Override
  public String encode(String password) {
    return innerPasswordEncoder.encode(password);
  }

  @Override
  public boolean validate(String password, String encoded) {
    return innerPasswordEncoder.matches(password, encoded);
  }
}
