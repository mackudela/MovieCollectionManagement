package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "role_name")
    @NotNull
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
