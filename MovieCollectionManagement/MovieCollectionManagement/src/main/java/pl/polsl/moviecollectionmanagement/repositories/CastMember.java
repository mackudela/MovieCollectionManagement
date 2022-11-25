package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastMember extends CrudRepository<CastMember, Long>, PagingAndSortingRepository<CastMember, Long> {
}
