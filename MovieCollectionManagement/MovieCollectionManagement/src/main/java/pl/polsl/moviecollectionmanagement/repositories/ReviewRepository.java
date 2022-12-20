package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.FavouriteMovie;
import pl.polsl.moviecollectionmanagement.entities.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>, PagingAndSortingRepository<Review, Long> {

    @Query(value = "SELECT * FROM reviews u WHERE u.movie_id = :movie_id",
            nativeQuery = true)
    List<Review> findAllByMovieId(@Param("movie_id") Long movie_id);
}
