package pl.polsl.moviecollectionmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.moviecollectionmanagement.entities.CastMember;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CastMemberDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String castRole;

    public CastMemberDto(CastMember castMember) {
        this.id = castMember.getId();
        this.firstName = castMember.getFirstName();
        this.lastName = castMember.getLastName();
        this.castRole = castMember.getCastRole();
    }
}
