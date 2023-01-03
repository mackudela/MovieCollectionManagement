package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.AnswerDto;
import pl.polsl.moviecollectionmanagement.dtos.ReviewDto;
import pl.polsl.moviecollectionmanagement.dtos.TopicDto;
import pl.polsl.moviecollectionmanagement.services.AnswerService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AnswerDto>> findAnswersByTopicId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(answerService.findAnswersByTopicId(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_FORUMPOST')")
    public ResponseEntity<Long> createAnswer(@RequestBody AnswerDto answerDto) {
        return new ResponseEntity<>(answerService.createAnswer(answerDto).getId(), HttpStatus.CREATED);
    }

    @Transactional
    @PreAuthorize("hasAuthority('DELETE_FORUMPOST')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable("id") Long id){
        answerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
