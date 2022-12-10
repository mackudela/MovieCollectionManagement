package pl.polsl.moviecollectionmanagement.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "favourite_movies")
public class FavouriteMovie extends BaseEntity{

    private Integer rating;

    @Column(name = "is_favourite")
    private Boolean isFavourite;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
}
