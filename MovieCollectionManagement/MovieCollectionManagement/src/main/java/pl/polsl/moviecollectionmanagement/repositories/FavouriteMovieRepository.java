package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import pl.polsl.moviecollectionmanagement.entities.FavouriteMovie;
import pl.polsl.moviecollectionmanagement.entities.Movie;

import java.util.List;

public interface FavouriteMovieRepository extends CrudRepository<FavouriteMovie, Long>, PagingAndSortingRepository<FavouriteMovie, Long> {

    @Query(value = "SELECT * FROM favourite_movies u WHERE u.user_id = :user_id and u.movie_id = :movie_id",
            nativeQuery = true)
    FavouriteMovie findFavouriteMovieByUserIdAndMovieId(@Param("user_id") Long userId, @Param("movie_id") Long movieId);

    @Query(value = "SELECT * FROM favourite_movies u WHERE u.user_id = :user_id",
            nativeQuery = true)
    List<FavouriteMovie> findAllByUserId(@Param("user_id") Long userId);
}
