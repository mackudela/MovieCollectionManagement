package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
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

    private Float rating;

    @Column(name = "year_of_production")
    private Integer yearOfProduction;

    @OneToMany(mappedBy = "movie")
    private Set<Review> reviews = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<CastMember> castMembers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "movie")
    private Set<FavouriteMovie> favouriteMovies = new LinkedHashSet<>();
}
