package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long>, PagingAndSortingRepository<Permission, Long> {
}
