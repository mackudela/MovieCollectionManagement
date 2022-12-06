package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.CastMember;

@Repository
public interface CastMemberRepository extends CrudRepository<CastMember, Long>, PagingAndSortingRepository<CastMember, Long> {
}
