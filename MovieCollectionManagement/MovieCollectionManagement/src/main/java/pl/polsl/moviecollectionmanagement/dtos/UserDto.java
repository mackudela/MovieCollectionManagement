package pl.polsl.moviecollectionmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String login;

    private String password;

    private String email;

    public UserDto(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
}
