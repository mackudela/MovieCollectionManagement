package pl.polsl.moviecollectionmanagement.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.FavouriteMovieDto;
import pl.polsl.moviecollectionmanagement.entities.FavouriteMovie;
import pl.polsl.moviecollectionmanagement.entities.Movie;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.repositories.FavouriteMovieRepository;
import pl.polsl.moviecollectionmanagement.repositories.MovieRepository;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class FavouriteMovieService {

    private final FavouriteMovieRepository favouriteMovieRepository;

    private final MovieRepository movieRepository;

    private final UserRepository userRepository;


    public FavouriteMovie addFavouriteMovie(String login, Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + movieId + " does not exist"));
        User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + login + " does not exist"));

        FavouriteMovie favouriteMovie = new FavouriteMovie();
        favouriteMovie.setIsFavourite(true);
        favouriteMovie.setUser(user);
        favouriteMovie.setMovie(movie);

        movie.getFavouriteMovies().add(favouriteMovie);
        user.getFavouriteMovies().add(favouriteMovie);
        return favouriteMovieRepository.save(favouriteMovie);
    }

    public boolean isFavouriteMovie(String login, Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie with id " + movieId + " does not exist"));
        User user = userRepository.findUserByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User with username " + login + " does not exist"));

        FavouriteMovie favouriteMovie = favouriteMovieRepository.findFavouriteMovieByUserIdAndMovieId(user.getId(), movieId);
        if (favouriteMovie == null)
            return false;
        return true;
    }

    public List<FavouriteMovieDto> getFavouriteMoviesList() {
        List<FavouriteMovie> favMovies = (List<FavouriteMovie>) favouriteMovieRepository.findAll();
        List<FavouriteMovieDto> dtos = new ArrayList<FavouriteMovieDto>();
        for(FavouriteMovie movie : favMovies) {
            FavouriteMovieDto newDto = new FavouriteMovieDto(movie);
            dtos.add(newDto);
        }
        return dtos;
    }

    public List<FavouriteMovieDto> getFavouriteMoviesListByUserId(Long userId) {
        List<FavouriteMovie> favMovies = favouriteMovieRepository.findAllByUserId(userId);
        List<FavouriteMovieDto> dtos = new ArrayList<FavouriteMovieDto>();
        for(FavouriteMovie movie : favMovies) {
            FavouriteMovieDto newDto = new FavouriteMovieDto(movie);
            dtos.add(newDto);
        }
        return dtos;
    }
}
