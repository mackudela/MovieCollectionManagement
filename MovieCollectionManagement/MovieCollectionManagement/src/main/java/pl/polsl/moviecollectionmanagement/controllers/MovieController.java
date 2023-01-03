package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.MovieDto;
import pl.polsl.moviecollectionmanagement.dtos.ReviewDto;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.entities.Review;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.services.MovieService;
import pl.polsl.moviecollectionmanagement.services.ReviewService;
import pl.polsl.moviecollectionmanagement.services.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

    private final MovieService movieService;

    private final ReviewService reviewService;

    @GetMapping("/all")
    public ResponseEntity<Page<MovieDto>> findAllMovies(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<MovieDto> movies = movieService.findAll(wholePage);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/all-shows")
    public ResponseEntity<Page<MovieDto>> findAllShows(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<MovieDto> movies = movieService.findAllShows(wholePage);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable("id") Long id) {
        MovieDto movieDto = movieService.getDto(id);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<ReviewDto>> findReviewsByMovieId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reviewService.findReviewsByMovie(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_REVIEW')")
    @PostMapping("/review/create")
    public ResponseEntity<Long> createReview(@RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(reviewDto).getId(), HttpStatus.CREATED);
    }

    @Transactional
    @PreAuthorize("hasAuthority('DELETE_MOVIE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id){
        movieService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('CREATE_MOVIE')")
    @PostMapping("/create")
    public ResponseEntity<Long> createMovie(@RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.createMovie(movieDto).getId(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('CREATE_MOVIE')")
    @PostMapping("/create-show")
    public ResponseEntity<Long> createTvShow(@RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.createTvShow(movieDto).getId(), HttpStatus.CREATED);
    }

    @Transactional
    @PreAuthorize("hasAuthority('DELETE_REVIEW')")
    @DeleteMapping("/review/delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable("id") Long id){
        reviewService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
