package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.enums.RoleName;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "roles")
public class Role extends BaseEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions",
            joinColumns = { @JoinColumn(name="role_id") },
            inverseJoinColumns = { @JoinColumn(name="permission_id") }
    )
    private Set<Permission> permissions = new LinkedHashSet<>();
}
