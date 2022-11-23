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

    @Column(name = "box_office")
    private Integer boxOffice;

    private Integer budget;

    @Column(name = "season_number")
    private Integer seasonNumber;

    @Column(name = "number_of_episodes")
    private Integer numberOfEpisodes;

    private String genre;

    @Column(name = "is_movie")
    private Boolean isMovie;

    @Column(name = "poster_url")
    private String posterUrl;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_movies",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
    private List<User> users;

    @ManyToMany(mappedBy = "movies")
    private List<CastMember> castMembers;
}
