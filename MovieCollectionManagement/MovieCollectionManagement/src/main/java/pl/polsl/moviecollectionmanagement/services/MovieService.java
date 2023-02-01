package pl.polsl.moviecollectionmanagement.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.MovieDto;
import pl.polsl.moviecollectionmanagement.entities.CastMember;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.repositories.CastMemberRepository;
import pl.polsl.moviecollectionmanagement.repositories.MovieRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepo;
    private final CastMemberRepository castMemberRepo;

    public Page<MovieDto> findAll(Pageable pageable) {
        if(movieRepo.findAllMovies(pageable).isPresent()){
            final Page<Movie> movies = movieRepo.findAllMovies(pageable).orElseThrow();
            return movies.map(MovieDto::new);
        }
        return null;
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
    public Movie createMovie(MovieDto movieDto) {
        final Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setBoxOffice(movieDto.getBoxOffice());
        movie.setSeasonNumber(0);
        movie.setNumberOfEpisodes(0);
        movie.setGenre(movieDto.getGenre());
        movie.setIsMovie(true);
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setLargePosterUrl(movieDto.getLargePosterUrl());
        movie.setRating(movieDto.getRating());
        movie.setYearOfProduction(movieDto.getYearOfProduction());
        movie.setMovieLength(movieDto.getMovieLength());
        for(Long id : movieDto.getCastIds()) {
            if(castMemberRepo.findById(id).isPresent()) {
                CastMember castMember = castMemberRepo.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Cast member with id " + id + " does not exist"));
                castMember.getMovies().add(movie);
                movie.getCastMembers().add(castMember);
            }
        }
        return movieRepo.save(movie);
    }

    @Transactional
    public Movie createTvShow(MovieDto movieDto) {
        final Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setBoxOffice(0f);
        movie.setSeasonNumber(movieDto.getSeasonNumber());
        movie.setNumberOfEpisodes(0);
        movie.setGenre(movieDto.getGenre());
        movie.setIsMovie(false);
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setLargePosterUrl(movieDto.getLargePosterUrl());
        movie.setRating(movieDto.getRating());
        movie.setYearOfProduction(movieDto.getYearOfProduction());
        movie.setMovieLength("");
        for(Long id : movieDto.getCastIds()) {
            if(castMemberRepo.findById(id).isPresent()) {
                CastMember castMember = castMemberRepo.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Cast member with id " + id + " does not exist"));

                castMember.getMovies().add(movie);
                movie.getCastMembers().add(castMember);
            }
        }
        return movieRepo.save(movie);
    }

    @Transactional
    public void deleteById(Long id) {
        Movie movie = movieRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + id + " does not exist"));
        Set<CastMember> cast = movie.getCastMembers();
        for (CastMember member : cast) {
            CastMember mem = castMemberRepo.findById(member.getId())
                    .orElseThrow();
            mem.getMovies().remove(movie);
        }
        movie.getCastMembers().removeAll(movie.getCastMembers());
        movieRepo.delete(movie);
    }

    public Page<MovieDto> findAllShows(Pageable pageable) {
        if(movieRepo.findAllShows(pageable).isPresent()){
            final Page<Movie> movies = movieRepo.findAllShows(pageable).orElseThrow();
            return movies.map(MovieDto::new);
        }
        return null;
    }
}
