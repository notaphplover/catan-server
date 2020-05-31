package io.github.notaphplover.catanserver.user.adapter.jwt.model;

public class UserTokenJwt {
  private String subject;

  private IUserTokenJwtClaims claims;

  public UserTokenJwt(String subject, IUserTokenJwtClaims claims) {
    this.subject = subject;
    this.claims = claims;
  }

  public String getSubject() {
    return subject;
  }

  public IUserTokenJwtClaims getClaims() {
    return claims;
  }
}
