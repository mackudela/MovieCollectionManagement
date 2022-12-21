package pl.polsl.moviecollectionmanagement.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.ReviewDto;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.entities.Review;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.repositories.MovieRepository;
import pl.polsl.moviecollectionmanagement.repositories.ReviewRepository;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final MovieRepository movieRepository;

    public List<ReviewDto> findReviewsByMovie(Long movieId) {
        List<Review> reviews = reviewRepository.findAllByMovieId(movieId);
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDto reviewDto = new ReviewDto(review);
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }

    @Transactional
    public Review createReview(ReviewDto reviewDto) {
//        log.info(Long.toString(reviewDto.getUserId()));
//        log.info(Long.toString(reviewDto.getMovieId()));
        User user = userRepository.findById(reviewDto.getUserId()).orElseThrow();
        Movie movie = movieRepository.findById(reviewDto.getMovieId()).orElseThrow();

        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setContent(reviewDto.getContent());

        user.getReviews().add(review);
        movie.getReviews().add(review);

        return reviewRepository.save(review);
    }
}
