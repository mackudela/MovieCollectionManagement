package pl.polsl.moviecollectionmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.entities.Review;
import pl.polsl.moviecollectionmanagement.entities.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {

    private Long id;

    private String content;

    private Long userId;

    private Long movieId;

    public ReviewDto(Review review) {
        setId(review.getId());
        setContent(review.getContent());
        setUserId(review.getUser().getId());
        setMovieId(review.getMovie().getId());
    }
}
