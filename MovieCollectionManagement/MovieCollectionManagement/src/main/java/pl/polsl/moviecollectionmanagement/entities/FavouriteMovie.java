package pl.polsl.moviecollectionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favourite_movies")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class FavouriteMovie extends BaseEntity{

    private Integer rating;

    @Column(name = "is_favourite")
    private Boolean isFavourite;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
}
