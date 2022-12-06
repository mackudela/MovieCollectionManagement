package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.CastRole;

@Repository
public interface CastRoleRepository extends CrudRepository<CastRole, Long>, PagingAndSortingRepository<CastRole, Long> {
}
