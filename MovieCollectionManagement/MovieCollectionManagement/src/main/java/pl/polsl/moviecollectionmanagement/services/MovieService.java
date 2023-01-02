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
import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepo;
    private final CastMemberRepository castMemberRepo;

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

//    @Transactional
//    public Movie create() {
//        final Movie movie = new Movie();
//        movie.setTitle("Pulp Fiction");
//        movie.setDescription("The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.");
//        movie.setBoxOffice(213.9f);
//        movie.setSeasonNumber(0);
//        movie.setNumberOfEpisodes(0);
//        movie.setGenre("Crime");
//        movie.setIsMovie(true);
//        movie.setPosterUrl("https://posters.movieposterdb.com/07_10/1994/110912/l_110912_55345443.jpg");
//        movie.setMovieLength("2h 34m");
//        movie.setLargePosterUrl("https://rare-gallery.com/mocahbig/72705-Pulp-Fiction-HD-Wallpaper.jpg");
//        movie.setRating(8.9f);
//        movie.setYearOfProduction(1994);
//
//        ArrayList<CastMember> cast = new ArrayList<>();
//
//        CastMember castMember = new CastMember();
//        castMember.setFirstName("John");
//        castMember.setLastName("Travolta");
//        castMember.setCastRole("Actor");
//        castMember.getMovies().add(movie);
//        cast.add(castMember);
//
//        CastMember castMember2 = new CastMember();
//        castMember2.setFirstName("Uma");
//        castMember2.setLastName("Thurman");
//        castMember2.setCastRole("Actor");
//        castMember2.getMovies().add(movie);
//        cast.add(castMember2);
//
//        CastMember castMember3 = new CastMember();
//        castMember3.setFirstName("Samuel");
//        castMember3.setLastName("Jackson");
//        castMember3.setCastRole("Actor");
//        castMember3.getMovies().add(movie);
//        cast.add(castMember3);
//
//        CastMember castMember4 = new CastMember();
//        castMember4.setFirstName("Quentin");
//        castMember4.setLastName("Tarantino");
//        castMember4.setCastRole("Director");
//        castMember4.getMovies().add(movie);
//        cast.add(castMember4);
//
//        CastMember castMember5 = new CastMember();
//        castMember5.setFirstName("Quentin");
//        castMember5.setLastName("Tarantino");
//        castMember5.setCastRole("Writer");
//        castMember5.getMovies().add(movie);
//        cast.add(castMember5);
//
//        CastMember castMember6 = new CastMember();
//        castMember6.setFirstName("Roger");
//        castMember6.setLastName("Avary");
//        castMember6.setCastRole("Writer");
//        castMember6.getMovies().add(movie);
//        cast.add(castMember6);
//
////        CastMember castMember7 = new CastMember();
////        castMember7.setFirstName("David");
////        castMember7.setLastName("Goyer");
////        castMember7.setCastRole("Writer");
////        castMember7.getMovies().add(movie);
////        cast.add(castMember7);
//
//
//
//        movie.getCastMembers().addAll(cast);
//
//        castMemberRepo.saveAll(cast);
//        return movieRepo.save(movie);
//    }

    @Transactional
    public void addCast() {
        ArrayList<CastMember> cast = new ArrayList<>();

        CastMember castMember = new CastMember();
        castMember.setFirstName("Tim");
        castMember.setLastName("Robins");

        cast.add(castMember);

        CastMember castMember2 = new CastMember();
        castMember2.setFirstName("Morgan");
        castMember2.setLastName("Freeman");

        cast.add(castMember2);

        CastMember castMember3 = new CastMember();
        castMember3.setFirstName("Bob");
        castMember3.setLastName("Gunton");

        cast.add(castMember3);

        castMemberRepo.saveAll(cast);
    }

    @Transactional
    public Movie createMovie(MovieDto movieDto) {
        final Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movie.getDescription());
        movie.setBoxOffice(movieDto.getBoxOffice());
        movie.setSeasonNumber(0);
        movie.setNumberOfEpisodes(0);
        movie.setGenre(movieDto.getGenre());
        movie.setIsMovie(true);
        movie.setPosterUrl(movieDto.getPosterUrl());
        movie.setLargePosterUrl(movie.getLargePosterUrl());
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


}
