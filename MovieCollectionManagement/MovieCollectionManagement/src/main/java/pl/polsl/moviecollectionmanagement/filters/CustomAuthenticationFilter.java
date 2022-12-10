package pl.polsl.moviecollectionmanagement.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import com.google.gson.Gson;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.polsl.moviecollectionmanagement.authentication.JwtResponse;
import pl.polsl.moviecollectionmanagement.configuration.SecurityUserPrincipal;
import pl.polsl.moviecollectionmanagement.entities.Role;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static pl.polsl.moviecollectionmanagement.configuration.SecurityConstants.EXPIRATION_TIME;
import static pl.polsl.moviecollectionmanagement.configuration.SecurityConstants.SECRET;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        log.info(login, password);
        logger.info(login + password);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {

        SecurityUserPrincipal user = (SecurityUserPrincipal) authentication.getPrincipal();
        Set<Role> rolesSet = user.getUser().getRoles();

        String roles = String.valueOf(user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        roles = roles.replaceAll("\\s+","");

        Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", roles)
                .sign(algorithm);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(user.getUser().getId());
        jwtResponse.setRoles(rolesSet.stream().map(role->role.getName().name()).collect(Collectors.toSet()));
        jwtResponse.setToken(token);

        String jsonString = new Gson().toJson(jwtResponse);
        response.getWriter().write(jsonString);
        response.getWriter().flush();
    }
}
