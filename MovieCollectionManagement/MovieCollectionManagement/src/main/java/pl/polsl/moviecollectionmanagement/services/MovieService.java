package pl.polsl.moviecollectionmanagement.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.MovieDto;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.repositories.MovieRepository;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepo;

    public Page<MovieDto> findAll(Pageable pageable) {
        final Page<Movie> movies = movieRepo.findAll(pageable);
        return movies.map(MovieDto::new);
    }

    public Movie findById(Long id) {
        return movieRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + id + " does not exist"));
    }

    public MovieDto getDto(Long id) {
        final Movie movie = findById(id);
        return new MovieDto(movie);
    }

    @Transactional
    public Movie create(MovieDto movieDto) {
        final Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setBoxOffice(movieDto.getBoxOffice());
        movie.setBudget(movieDto.getBudget());
        movie.setSeasonNumber(movieDto.getSeasonNumber());
        movie.setNumberOfEpisodes(movieDto.getNumberOfEpisodes());
        movie.setGenre(movieDto.getGenre());
        movie.setIsMovie(movieDto.getIsMovie());
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setRating(movieDto.getRating());
        return movieRepo.save(movie);
    }


}
