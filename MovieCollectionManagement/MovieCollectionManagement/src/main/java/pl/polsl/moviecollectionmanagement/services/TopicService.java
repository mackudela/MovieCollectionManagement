package pl.polsl.moviecollectionmanagement.services;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.TopicDto;
import pl.polsl.moviecollectionmanagement.entities.Topic;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.repositories.TopicRepository;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;

    private final UserRepository userRepository;

    public List<TopicDto> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        List<TopicDto> dtos = new ArrayList<>();
        for(Topic topic : topics) {
            TopicDto dto = new TopicDto(topic);
            dtos.add(dto);
        }
        return dtos;
    }

    public Topic createTopic(TopicDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + dto.getUserId() + " does not exist"));

        Topic topic = new Topic();
        topic.setName(dto.getName());
        topic.setContent(dto.getContent());
        topic.setUser(user);

        user.getTopics().add(topic);

        return topicRepository.save(topic);
    }

    public TopicDto getTopicById(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic with id " + id + " does not exist"));

        TopicDto dto = new TopicDto(topic);
        return dto;

    }

    public void deleteById(Long id) {
        topicRepository.deleteById(id);
    }
}
