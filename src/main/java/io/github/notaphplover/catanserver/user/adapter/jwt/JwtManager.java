package io.github.notaphplover.catanserver.user.adapter.jwt;

import io.github.notaphplover.catanserver.user.adapter.jwt.exception.UnableToGenerateTokenException;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.IUserTokenJwtClaims;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwt;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwtClaims;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.UserTokenJwtClaims_;
import io.github.notaphplover.catanserver.user.domain.model.IUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtManager implements Serializable {

  private static final String CLAIM_NAMESPACE = "https://github.com/notaphplover/catan-server/";

  private static final long serialVersionUID = -2550185165627007488L;
  private static final long JWT_TOKEN_VALIDITY_MS = 24 * 60 * 60 * 1000;

  @Value("${jwt.secret}")
  private String secret;

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
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY_MS))
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  public Optional<UserTokenJwt> validateAndGet(String token) {
    Optional<UserTokenJwt> userTokenJwtCapsule = null;

    Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

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
}
