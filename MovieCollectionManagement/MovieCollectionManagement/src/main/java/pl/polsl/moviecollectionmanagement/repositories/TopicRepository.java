package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.moviecollectionmanagement.entities.Topic;

import java.util.List;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long>, PagingAndSortingRepository<Topic, Long> {
    List<Topic> findAll();
}
