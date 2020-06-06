package io.github.notaphplover.catanserver.user.adapter.jwt;

import io.github.notaphplover.catanserver.common.service.IDateService;
import io.github.notaphplover.catanserver.user.adapter.jwt.exception.ExpiredTokenException;
import io.github.notaphplover.catanserver.user.adapter.jwt.exception.UnableToGenerateTokenException;
import io.github.notaphplover.catanserver.user.adapter.jwt.exception.WrongSignatureTokenException;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.IUserTokenJwt;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.IUserTokenJwtClaims;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwt;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwtClaims;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwtClaims_;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.jsonwebtoken.*;
import java.io.Serializable;
import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtManager implements Serializable {

  private static final String CLAIM_NAMESPACE = "https://github.com/notaphplover/catan-server/";

  private static final long serialVersionUID = -2550185165627007488L;
  private static final long JWT_TOKEN_VALIDITY_MS = 24 * 60 * 60 * 1000;

  private final IDateService dateService;

  private final JwtParser jwtParser;

  private final String secret;

  public JwtManager(IDateService dateService, @Value("${jwt.secret}") String secret) {
    this.dateService = dateService;
    this.jwtParser = generateJwtParser(dateService, secret);
    this.secret = secret;
  }

  public String generateToken(IUser user) {

    if (user.getId() == null) {
      throw new UnableToGenerateTokenException("Unable to generate a token from a user with no id");
    }

    Map<String, Object> claims = new HashMap<>();

    IUserTokenJwtClaims namespaceClaims = new UserTokenJwtClaims(user.getId(), user.getUsername());

    claims.put(CLAIM_NAMESPACE, namespaceClaims);

    return Jwts.builder()
        .setClaims(claims)
        .setSubject(user.getUsername())
        .setIssuedAt(dateService.getCurrent())
        .setExpiration(dateService.getWithOffset(JWT_TOKEN_VALIDITY_MS))
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  public Optional<IUserTokenJwt> validateAndGet(String token) {
    Optional<IUserTokenJwt> userTokenJwtCapsule = null;

    Claims claims;

    try {
      claims = jwtParser.parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException exception) {
      throw new ExpiredTokenException(exception);
    } catch (SignatureException exception) {
      throw new WrongSignatureTokenException(exception);
    }

    String subject = claims.getSubject();

    @SuppressWarnings("unchecked")
    AbstractMap<String, Object> userTokenClaimsMap =
        (AbstractMap<String, Object>) claims.get(CLAIM_NAMESPACE, AbstractMap.class);

    long id = (Integer) userTokenClaimsMap.get(UserTokenJwtClaims_.ID);

    String username = (String) userTokenClaimsMap.get(UserTokenJwtClaims_.USERNAME);

    IUserTokenJwtClaims userTokenClaims = new UserTokenJwtClaims(id, username);

    try {
      userTokenJwtCapsule = Optional.of(new UserTokenJwt(subject, userTokenClaims));
    } catch (IllegalArgumentException e) {
      System.out.println("Unable to get JWT Token");
      userTokenJwtCapsule = Optional.empty();
    } catch (ExpiredJwtException e) {
      System.out.println("JWT Token has expired");
      userTokenJwtCapsule = Optional.empty();
    } catch (Exception ex) {
      System.out.println("An unexpected error ocurred");
      userTokenJwtCapsule = Optional.empty();
    }

    return userTokenJwtCapsule;
  }

  private JwtParser generateJwtParser(IDateService dateService, String secret) {
    return Jwts.parser()
        .setSigningKey(secret)
        .setClock(
            new Clock() {
              @Override
              public Date now() {
                return dateService.getCurrent();
              }
            });
  }
}
