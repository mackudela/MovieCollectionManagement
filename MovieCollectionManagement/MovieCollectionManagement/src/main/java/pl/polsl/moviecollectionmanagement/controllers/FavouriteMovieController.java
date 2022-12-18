package pl.polsl.moviecollectionmanagement.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.FavouriteMovieDto;
import pl.polsl.moviecollectionmanagement.entities.FavouriteMovie;
import pl.polsl.moviecollectionmanagement.services.FavouriteMovieService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/favourite_movie")
public class FavouriteMovieController {

    private final FavouriteMovieService favouriteMovieService;

    @Transactional(readOnly = false)
    @PostMapping("/create")
    public ResponseEntity<Long> createFavouriteMovie(@RequestBody Map<String, String> json) { //String login, @RequestBody Long movieId
        log.info(json.get("login"));
        log.info(json.get("movieId"));
        return new ResponseEntity<>(favouriteMovieService.addFavouriteMovie(json.get("login"), Long.parseLong(json.get("movieId"))).getId(), HttpStatus.CREATED);
    }

    @GetMapping("/is_favourite")
    public ResponseEntity<Boolean> isFavouriteMovie(@RequestParam String login, Long movieId) {
        return new ResponseEntity<>(favouriteMovieService.isFavouriteMovie(login, movieId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FavouriteMovieDto>> getFavouriteMoviesList() {
        return new ResponseEntity<>(favouriteMovieService.getFavouriteMoviesList(), HttpStatus.OK);
    }


    @GetMapping("/all/{id}")
    public ResponseEntity<List<FavouriteMovieDto>> getFavouriteMoviesListByUserId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(favouriteMovieService.getFavouriteMoviesListByUserId(id), HttpStatus.OK);
    }
}
