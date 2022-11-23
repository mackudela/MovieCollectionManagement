package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    @NotNull
    private String login;

    @NotNull
    private String password;

    @Column(unique = true)
    @NotNull
    private String email;


    private Long roleId;
}
