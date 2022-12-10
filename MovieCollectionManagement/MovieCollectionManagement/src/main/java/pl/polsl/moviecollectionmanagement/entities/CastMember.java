package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "movies_cast_members",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cast_member_id", referencedColumnName = "id"))
    private List<Movie> movies;

//    @ManyToMany(mappedBy = "castMembers")
//    private List<CastRole> castRoles;
}
