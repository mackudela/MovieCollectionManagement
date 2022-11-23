package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "cast_roles")
public class CastRole extends BaseEntity {

    @Column(name = "role_name")
    @NotNull
    private String roleName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cast_members_cast_roles",
            joinColumns = @JoinColumn(name = "cast_member_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cast_role_id", referencedColumnName = "id"))
    private List<CastMember> castMembers;
}
