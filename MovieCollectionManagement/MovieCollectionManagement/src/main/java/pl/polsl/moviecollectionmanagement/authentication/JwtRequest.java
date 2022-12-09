package pl.polsl.moviecollectionmanagement.authentication;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JwtRequest {

    private String login;

    private String password;
}
