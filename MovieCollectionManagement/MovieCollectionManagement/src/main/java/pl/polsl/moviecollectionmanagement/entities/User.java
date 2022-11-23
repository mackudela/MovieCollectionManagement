package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Topic> topics;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    @ManyToMany(mappedBy = "users")
    private List<Movie> movies;
}
