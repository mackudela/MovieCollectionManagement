package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "movies")
public class Movie extends BaseEntity {

    @NotNull
    private String title;

    private String description;

    @Column(name = "box_office")
    private Integer boxOffice;

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

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "movies")
    private List<CastMember> castMembers;

    @OneToMany(mappedBy = "movie")
    private List<FavouriteMovie> favouriteMovies;
}
