package pl.polsl.moviecollectionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movies")
public class Movie extends BaseEntity {

    @NotNull
    private String title;

    private String description;

    @Column(name = "box_office")
    private Float boxOffice;

    @Column(name = "season_number")
    private Integer seasonNumber;

    @Column(name = "number_of_episodes")
    private Integer numberOfEpisodes;

    private String genre;

    @Column(name = "is_movie")
    private Boolean isMovie;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "large_poster_url")
    private String largePosterUrl;

    private Float rating;

    @Column(name = "year_of_production")
    private Integer yearOfProduction;

    @Column(name = "movie_length")
    private String movieLength;

    @JsonManagedReference("Movie-Review")
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Review> reviews = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<CastMember> castMembers = new LinkedHashSet<>();


    @JsonManagedReference("Movie-FavouriteMovie")
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<FavouriteMovie> favouriteMovies = new LinkedHashSet<>();
}
