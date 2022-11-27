package pl.polsl.moviecollectionmanagement.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User,Long> {
    void deleteUserById(Long id);
}