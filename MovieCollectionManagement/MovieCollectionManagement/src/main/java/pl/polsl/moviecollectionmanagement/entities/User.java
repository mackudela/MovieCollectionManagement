package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Topic> topics = new LinkedHashSet<>();;

    @OneToMany(mappedBy = "user")
    private Set<Answer> answers = new LinkedHashSet<>();;

    @OneToMany(mappedBy = "user")
    private Set<FavouriteMovie> favouriteMovies = new LinkedHashSet<>();;
}
