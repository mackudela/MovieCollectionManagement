package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "roles_users",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Topic> topics;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    @OneToMany(mappedBy = "user")
    private List<FavouriteMovie> favouriteMovies;
}
