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
import pl.polsl.moviecollectionmanagement.services.MovieService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;


    @GetMapping("/all")
    public ResponseEntity<Page<MovieDto>> findAllMovies(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<MovieDto> movies = movieService.findAll(wholePage);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MovieDto> findUserById(@PathVariable("id") Long id) {
        MovieDto movieDto = movieService.getDto(id);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createMovie(@RequestBody MovieDto movieDto) {
        return new ResponseEntity<>(movieService.create(movieDto).getId(), HttpStatus.CREATED);
    }
}
