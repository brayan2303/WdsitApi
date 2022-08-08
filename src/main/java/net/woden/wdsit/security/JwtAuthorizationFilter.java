package net.woden.wdsit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.woden.wdsit.util.UserRepositoryToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String SECRET;
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    
    @Resource
    private UserRepositoryToken userRepository;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (existeJWTToken(request, response)) {
                Claims claims = validateToken(request);
                String user = (String) claims.get("sub");
                String userValidate = "";
                userValidate = userRepository.findByUsername(user);
                if(userValidate == null){
                   userValidate = "";
                }
                String path = request.getServletPath();
                if (path.contains("logOut") == true) {
                    if (claims.get("sub") != null && userValidate.equals("") == false) {
                        userRepository.remove(user);
                        setUpSpringAuthentication(claims);
                    } else {
                        SecurityContextHolder.clearContext();
                    }
                } else {
                        if (claims.get("sub") != null && userValidate.equals("") == false) {
                            setUpSpringAuthentication(claims);
                        } else {
                            SecurityContextHolder.clearContext();
                        }
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
            // Token
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        }
    }

//Si existe el token, lo desencripta y lo valida
    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        Claims validateToken = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody();
        return validateToken;
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     *
     * @param claims
     */
    //Autorización de peticiones  
    private void setUpSpringAuthentication(Claims claims) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("role"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    //Comprobar la existencia del Token
    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
            return false;
        }
        return true;
    }
}
