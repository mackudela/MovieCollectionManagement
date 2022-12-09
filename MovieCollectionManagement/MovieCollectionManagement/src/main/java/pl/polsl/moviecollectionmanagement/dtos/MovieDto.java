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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Long id;

    private String title;


    private String description;

    private Integer boxOffice;

    private Integer budget;

    private Integer seasonNumber;

    private Integer numberOfEpisodes;

    private String genre;

    private Boolean isMovie;

    private String posterUrl;

    private Float rating;

    private List<Review> reviews;

    private List<User> users;

    private List<CastMember> castMembers;

    public MovieDto(Movie movie){
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.boxOffice = movie.getBoxOffice();
        this.budget = movie.getBudget();
        this.seasonNumber = movie.getSeasonNumber();
        this.numberOfEpisodes = movie.getNumberOfEpisodes();
        this.genre = movie.getGenre();
        this.isMovie = movie.getIsMovie();
        this.posterUrl = movie.getPosterUrl();
        this.rating = movie.getRating();
        this.reviews = movie.getReviews();
        this.users = movie.getUsers();
        this.castMembers = movie.getCastMembers();
    }
}
