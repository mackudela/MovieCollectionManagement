package pl.polsl.moviecollectionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity(name = "reviews")
public class Review extends BaseEntity {

    @NotNull
    @Lob
    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @NotNull
    private Movie movie;
}
