package pl.polsl.moviecollectionmanagement.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponse {
    private Long id;
    private String token;
    private Set<String> roles;
}
