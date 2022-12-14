package pl.polsl.moviecollectionmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.entities.CastMember;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.entities.Review;
import pl.polsl.moviecollectionmanagement.entities.User;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Long id;

    private String title;

    private String description;

    private Float boxOffice;

    private Integer seasonNumber;

    private Integer numberOfEpisodes;

    private Integer yearOfProduction;

    private String genre;

    private Boolean isMovie;

    private String posterUrl;

    private Float rating;

    private Set<Review> reviews;

    private Set<CastMember> castMembers;

    public MovieDto(Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.boxOffice = movie.getBoxOffice();
        this.seasonNumber = movie.getSeasonNumber();
        this.numberOfEpisodes = movie.getNumberOfEpisodes();
        this.yearOfProduction = movie.getYearOfProduction();
        this.genre = movie.getGenre();
        this.isMovie = movie.getIsMovie();
        this.posterUrl = movie.getPosterUrl();
        this.rating = movie.getRating();
        this.reviews = movie.getReviews();
        //this.castMembers = movie.getCastMembers();
    }
}
