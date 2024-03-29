package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.TopicDto;
import pl.polsl.moviecollectionmanagement.services.TopicService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    @GetMapping("/all")
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopicById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(topicService.getTopicById(id), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_FORUMPOST')")
    public ResponseEntity<Long> createTopic(@RequestBody TopicDto topicDto) {
        return new ResponseEntity<>(topicService.createTopic(topicDto).getId(), HttpStatus.CREATED);
    }

    @Transactional
    @PreAuthorize("hasAuthority('DELETE_FORUMPOST')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTopic(@PathVariable("id") Long id){
        topicService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
