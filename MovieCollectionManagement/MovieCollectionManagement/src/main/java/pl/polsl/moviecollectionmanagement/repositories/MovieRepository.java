package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.Movie;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, PagingAndSortingRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movies u WHERE u.is_movie =false",
            nativeQuery = true)
    Optional<Page<Movie>> findAllShows(Pageable pageable);

    @Query(value = "SELECT * FROM movies u WHERE u.is_movie =true",
            nativeQuery = true)
    Optional<Page<Movie>> findAllMovies(Pageable pageable);
}
