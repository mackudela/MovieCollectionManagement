package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.MovieDto;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.Review;
import pl.polsl.moviecollectionmanagement.services.MovieService;
import pl.polsl.moviecollectionmanagement.services.ReviewService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    private final ReviewService reviewService;

    @GetMapping("/all")
    public ResponseEntity<Page<MovieDto>> findAllMovies(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<MovieDto> movies = movieService.findAll(wholePage);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable("id") Long id) {
        MovieDto movieDto = movieService.getDto(id);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<Review>> findReviewsByMovieId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(reviewService.findReviewsByMovie(id), HttpStatus.OK);
    }

//    @PostMapping("/create") //simplified version
//    public ResponseEntity<Long> createMovie() {
//        return new ResponseEntity<>(movieService.create().getId(), HttpStatus.CREATED);
//    }

//    @PostMapping("/create")
//    public ResponseEntity<Long> createMovie(@RequestBody MovieDto movieDto) {
//        return new ResponseEntity<>(movieService.create(movieDto).getId(), HttpStatus.CREATED);
//    }
}
