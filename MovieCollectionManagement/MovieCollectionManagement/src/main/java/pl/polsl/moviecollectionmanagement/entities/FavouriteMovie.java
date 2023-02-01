package pl.polsl.moviecollectionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favourite_movies")
public class FavouriteMovie extends BaseEntity{

    private Integer rating;

    @Column(name = "is_favourite")
    private Boolean isFavourite;

    @JsonBackReference("Movie-FavouriteMovie")
    @ManyToOne()
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;

    @JsonBackReference("User-FavouriteMovie")
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
}
