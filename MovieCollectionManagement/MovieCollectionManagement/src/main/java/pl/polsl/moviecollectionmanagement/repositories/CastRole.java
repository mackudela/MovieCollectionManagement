package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastRole extends CrudRepository<CastRole, Long>, PagingAndSortingRepository<CastRole, Long> {
}
