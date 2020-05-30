package io.github.notaphplover.catanserver.user.adapter.jwt.model;

public class UserTokenJwt {
  private String subject;

  private UserTokenJwtClaims claims;

  public UserTokenJwt(String subject, UserTokenJwtClaims claims) {
    this.subject = subject;
    this.claims = claims;
  }

  public String getSubject() {
    return subject;
  }

  public UserTokenJwtClaims getClaims() {
    return claims;
  }
}
