package br.com.bixtecnologia.processadordeimagem.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @SuppressWarnings("unchecked")
	private List<SimpleGrantedAuthority> getAuthoritiesFromToken(Claims claims) {
        List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            logger.info("Token extracted: {}", token);

            try {
                // Validate token
                if (jwtUtil.validateToken(token)) {
                    logger.info("Token is valid.");
                    Claims claims = jwtUtil.extractClaims(token);
                    String username = claims.getSubject();

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        var userDetails = userDetailsService.loadUserByUsername(username);
                        List<SimpleGrantedAuthority> authorities = getAuthoritiesFromToken(claims); // Extraindo authorities

                        var authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, authorities
                        );
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        logger.info("User authenticated: {}", username);
                    }
                }
            } catch (ExpiredJwtException e) {
                logger.error("Token expired: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
                return;
            } catch (UnsupportedJwtException | MalformedJwtException e) {
                logger.error("Invalid token: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            } catch (Exception e) {
                logger.error("Error processing token: {}", e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro ao processar token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
