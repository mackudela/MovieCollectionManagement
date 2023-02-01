package pl.polsl.moviecollectionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
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

    @JsonManagedReference("User-Topic")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Topic> topics = new LinkedHashSet<>();

    @JsonManagedReference("User-Answer")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Answer> answers = new LinkedHashSet<>();

    @JsonManagedReference("User-FavouriteMovie")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<FavouriteMovie> favouriteMovies = new LinkedHashSet<>();

    @JsonManagedReference("User-Review")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews = new LinkedHashSet<>();
}
