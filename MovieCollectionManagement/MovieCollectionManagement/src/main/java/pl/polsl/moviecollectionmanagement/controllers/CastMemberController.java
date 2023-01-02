package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.moviecollectionmanagement.dtos.CastMemberDto;
import pl.polsl.moviecollectionmanagement.services.CastMemberService;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/cast_member")
public class CastMemberController {

    private final CastMemberService castMemberService;

    @Transactional(readOnly = false)
    @PreAuthorize("hasAuthority('CREATE_MOVIE')")
    @PostMapping("/create")
    public ResponseEntity<Long> createCastMembers(@RequestBody CastMemberDto dto) {
        return new ResponseEntity<>(castMemberService.createCastMember(dto), HttpStatus.CREATED);
    }
}
