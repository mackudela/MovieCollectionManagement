package pl.polsl.moviecollectionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "cast_members")
public class CastMember extends BaseEntity{

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "cast_role")
    private String castRole;

//    @Getter(AccessLevel.PRIVATE)
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "cast_members_movies",
            joinColumns = @JoinColumn(name = "cast_member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private Set<Movie> movies = new LinkedHashSet<>();

//    @ManyToMany(mappedBy = "castMembers")
//    private List<CastRole> castRoles;
}
