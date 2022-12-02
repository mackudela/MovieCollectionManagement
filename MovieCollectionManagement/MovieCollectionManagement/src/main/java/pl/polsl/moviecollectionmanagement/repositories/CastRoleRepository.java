package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRoleRepository extends CrudRepository<CastRoleRepository, Long>, PagingAndSortingRepository<CastRoleRepository, Long> {
}
