package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.Role;
import pl.polsl.moviecollectionmanagement.enums.RoleName;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {
    Role findByName(RoleName user);
}
