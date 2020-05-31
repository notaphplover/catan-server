package io.github.notaphplover.catanserver.common.adapter.api.config;

import io.github.notaphplover.catanserver.common.port.IPort;
import io.github.notaphplover.catanserver.user.adapter.jwt.JwtManager;
import io.github.notaphplover.catanserver.user.adapter.jwt.model.IUserTokenJwt;
import io.github.notaphplover.catanserver.user.domain.model.IUserToken;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired private JwtManager jwtTokenUtil;

  @Autowired IPort<IUserTokenJwt, IUserToken> userTokenJwtToUserTokenPort;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader("Authorization");

    String jwtToken = null;

    Optional<IUserTokenJwt> userTokenJwtCapsule = null;

    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      userTokenJwtCapsule = jwtTokenUtil.validateAndGet(jwtToken);
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }

    if (userTokenJwtCapsule != null
        && userTokenJwtCapsule.isPresent()
        && SecurityContextHolder.getContext().getAuthentication() == null) {
      IUserToken userToken = userTokenJwtToUserTokenPort.transform(userTokenJwtCapsule.get());

      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
          new UsernamePasswordAuthenticationToken(
              userToken, null, new LinkedList<GrantedAuthority>());
      usernamePasswordAuthenticationToken.setDetails(
          new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
    chain.doFilter(request, response);
  }
}
