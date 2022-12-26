package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.Answer;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long>, PagingAndSortingRepository<Answer, Long> {

    @Query(value = "SELECT * FROM answers u WHERE u.topic_id = :topic_id",
            nativeQuery = true)
    Optional<List<Answer>> findAllByTopicId(Long topic_id);
}
