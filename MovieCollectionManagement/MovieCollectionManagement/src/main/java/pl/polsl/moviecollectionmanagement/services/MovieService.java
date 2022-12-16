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
//        movie.setTitle("The Lord of the Rings: The Return of the King");
//        movie.setDescription("Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.");
//        movie.setBoxOffice(1146f);
//        movie.setSeasonNumber(0);
//        movie.setNumberOfEpisodes(0);
//        movie.setGenre("Adventure");
//        movie.setIsMovie(true);
//        movie.setPosterUrl("https://xl.movieposterdb.com/04_12/2003/0167260/xl_183_0167260_6815154e.jpg?v=2022-12-01%2016:36:24");
//        movie.setRating(0f);
//        movie.setYearOfProduction(2003);
//
//        ArrayList<CastMember> cast = new ArrayList<>();
//
//        CastMember castMember = new CastMember();
//        castMember.setFirstName("Elijah");
//        castMember.setLastName("Wood");
//        castMember.setCastRole("Actor");
//        castMember.getMovies().add(movie);
//        cast.add(castMember);
//
//        CastMember castMember2 = new CastMember();
//        castMember2.setFirstName("Viggo");
//        castMember2.setLastName("Mortensen");
//        castMember2.setCastRole("Actor");
//        castMember2.getMovies().add(movie);
//        cast.add(castMember2);
//
//        CastMember castMember3 = new CastMember();
//        castMember3.setFirstName("Ian");
//        castMember3.setLastName("McKellen");
//        castMember3.setCastRole("Actor");
//        castMember3.getMovies().add(movie);
//        cast.add(castMember3);
//
//        CastMember castMember4 = new CastMember();
//        castMember4.setFirstName("Peter");
//        castMember4.setLastName("Jackson");
//        castMember4.setCastRole("Director");
//        castMember4.getMovies().add(movie);
//        cast.add(castMember4);
//
//        CastMember castMember5 = new CastMember();
//        castMember5.setFirstName("John");
//        castMember5.setLastName("Tolkien");
//        castMember5.setCastRole("Writer");
//        castMember5.getMovies().add(movie);
//        cast.add(castMember5);
//
//        CastMember castMember6 = new CastMember();
//        castMember6.setFirstName("Fran");
//        castMember6.setLastName("Walsh");
//        castMember6.setCastRole("Writer");
//        castMember6.getMovies().add(movie);
//        cast.add(castMember6);
//
//        CastMember castMember7 = new CastMember();
//        castMember7.setFirstName("Philippa");
//        castMember7.setLastName("Boyens");
//        castMember7.setCastRole("Writer");
//        castMember7.getMovies().add(movie);
//        cast.add(castMember7);
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

//    @Transactional
//    public Movie create(MovieDto movieDto) {
//        final Movie movie = new Movie();
//        movie.setTitle(movieDto.getTitle());
//        movie.setBoxOffice(movieDto.getBoxOffice());
//        movie.setSeasonNumber(movieDto.getSeasonNumber());
//        movie.setNumberOfEpisodes(movieDto.getNumberOfEpisodes());
//        movie.setGenre(movieDto.getGenre());
//        movie.setIsMovie(movieDto.getIsMovie());
//        movie.setPosterUrl(movieDto.getPosterUrl());
//        movie.setRating(movieDto.getRating());
//
//        return movieRepo.save(movie);
//    }


}
