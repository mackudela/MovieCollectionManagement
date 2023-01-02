package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.CastMember;
import pl.polsl.moviecollectionmanagement.entities.FavouriteMovie;

import java.util.Optional;

@Repository
public interface CastMemberRepository extends CrudRepository<CastMember, Long>, PagingAndSortingRepository<CastMember, Long> {

    @Query(value = "SELECT * FROM cast_members u WHERE u.first_name = :first_name and u.last_name = :last_name and u.cast_role = :cast_role",
            nativeQuery = true)
    Optional<CastMember> findCastMemberByFirstNameLastNameCastRole(@Param("first_name") String first_name, @Param("last_name") String last_name, @Param("cast_role") String cast_role);
}
