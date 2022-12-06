package pl.polsl.moviecollectionmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.entities.CastMember;
import pl.polsl.moviecollectionmanagement.entities.Review;
import pl.polsl.moviecollectionmanagement.entities.User;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Long id;

    private String title;

    private Integer budget;

    private Integer seasonNumber;

    private Integer numberOfEpisodes;

    private String genre;

    private Boolean isMovie;

    private Float rating;

    private List<Review> reviews;

    private List<User> users;

    private List<CastMember> castMembers;
}
