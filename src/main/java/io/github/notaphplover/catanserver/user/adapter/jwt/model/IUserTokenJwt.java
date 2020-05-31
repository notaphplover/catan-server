package io.github.notaphplover.catanserver.user.adapter.jwt.model;

public interface IUserTokenJwt {

  String getSubject();

  IUserTokenJwtClaims getClaims();
}
