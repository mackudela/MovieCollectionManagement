package pl.polsl.moviecollectionmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.entities.FavouriteMovie;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavouriteMovieDto {

    private Long id;

    private Boolean isFavourite;

    private Movie movie;

    private User user;

    private Integer rating;

    public FavouriteMovieDto(FavouriteMovie favouriteMovie) {
        this.id = favouriteMovie.getId();
        this.isFavourite = favouriteMovie.getIsFavourite();
        this.movie = favouriteMovie.getMovie();
        this.user = favouriteMovie.getUser();
        this.rating = favouriteMovie.getRating();
    }
}
