package pl.polsl.moviecollectionmanagement.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.polsl.moviecollectionmanagement.dtos.AnswerDto;
import pl.polsl.moviecollectionmanagement.entities.Answer;
import pl.polsl.moviecollectionmanagement.entities.Topic;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.repositories.AnswerRepository;
import pl.polsl.moviecollectionmanagement.repositories.TopicRepository;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnswerService {

    private final AnswerRepository answerRepository;

    private final UserRepository userRepository;

    private final TopicRepository topicRepository;

    public List<AnswerDto> findAnswersByTopicId(Long id) {
        List<Answer> answers = answerRepository.findAllByTopicId(id)
                .orElseThrow(() -> new EntityNotFoundException("Topic (or answer) with id " + id + " does not exist"));
        List<AnswerDto> dtos = new ArrayList<>();

        for(Answer answer : answers) {
            AnswerDto dto = new AnswerDto(answer);
            dtos.add(dto);
        }
        return dtos;
    }

    public Answer createAnswer(AnswerDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User with id " + dto.getUserId() + " does not exist"));

        Topic topic = topicRepository.findById(dto.getTopicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic with id " + dto.getTopicId() + " does not exist"));

        Answer answer = new Answer();
        answer.setUser(user);
        answer.setTopic(topic);
        answer.setContent(dto.getContent());

        user.getAnswers().add(answer);
        topic.getAnswers().add(answer);

        return answerRepository.save(answer);
    }

    public void deleteById(Long id) {
        answerRepository.deleteById(id);
    }
}
