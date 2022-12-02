package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositoryRepository extends CrudRepository<MovieRepositoryRepository, Long>, PagingAndSortingRepository<MovieRepositoryRepository, Long> {
}
