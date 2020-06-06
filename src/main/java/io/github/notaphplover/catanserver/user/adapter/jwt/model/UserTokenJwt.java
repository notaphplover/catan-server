package io.github.notaphplover.catanserver.user.adapter.jwt.model;

public class UserTokenJwt implements IUserTokenJwt {
  private String subject;

  private IUserTokenJwtClaims claims;

  public UserTokenJwt(String subject, IUserTokenJwtClaims claims) {
    this.subject = subject;
    this.claims = claims;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof IUserTokenJwt)) {
      return false;
    }

    IUserTokenJwt that = (IUserTokenJwt)obj;

    return that.getSubject().equals(getSubject()) && that.getClaims().equals(getClaims());
  }

  public String getSubject() {
    return subject;
  }

  public IUserTokenJwtClaims getClaims() {
    return claims;
  }
}
