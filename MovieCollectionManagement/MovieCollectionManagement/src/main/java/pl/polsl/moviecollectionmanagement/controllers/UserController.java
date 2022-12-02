package pl.polsl.moviecollectionmanagement.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.Role;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> findAllUsers(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<UserDto> users = userService.findAll(wholePage);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id) {
        UserDto userDto = userService.getDto(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto).getId(), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        userService.update(userDto.getId(), userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String login = decodedJWT.getSubject();
                User user = userService.findByLogin(login);
                ArrayList<Role> userRoles = new ArrayList<>();
                userRoles.add(user.getRole());
                String accessToken = JWT.create()
                        .withSubject(user.getLogin())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) //10 minutes
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", userRoles.stream().map(Role::getRoleName).collect(Collectors.toList()))
                        .sign(algorithm);

//        response.setHeader("accessToken", accessToken);
//        response.setHeader("refreshToken", refreshToken);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("errorMessage", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
