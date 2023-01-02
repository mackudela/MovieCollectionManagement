package pl.polsl.moviecollectionmanagement.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.polsl.moviecollectionmanagement.dtos.CastMemberDto;
import pl.polsl.moviecollectionmanagement.entities.CastMember;
import pl.polsl.moviecollectionmanagement.repositories.CastMemberRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CastMemberService {

    private final CastMemberRepository castMemberRepository;

    public Long createCastMember(CastMemberDto dto) {
        if(this.castMemberRepository.findCastMemberByFirstNameLastNameCastRole(dto.getFirstName(), dto.getLastName(), dto.getCastRole()).isPresent()){
            return this.castMemberRepository.findCastMemberByFirstNameLastNameCastRole(dto.getFirstName(), dto.getLastName(), dto.getCastRole()).get().getId();
        } else {
            CastMember castMember = new CastMember();
            castMember.setFirstName(dto.getFirstName());
            castMember.setLastName(dto.getLastName());
            castMember.setCastRole(dto.getCastRole());
            return this.castMemberRepository.save(castMember).getId();
        }
    }
}
