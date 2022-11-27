package pl.polsl.moviecollectionmanagement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public Page<UserDto> findAll(Pageable pageable) {
        final Page<User> users = userRepo.findAll(pageable);
        return users.map(UserDto::new);
        //final List<User> users = userRepo.findAll();
        //return users.
    }

    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " does not exist"));
    }

    public UserDto getDto(Long id) {
        final User user = findById(id);
        return new UserDto(user);
    }

    @Transactional
    public User create(UserDto userDto) {
        final User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return userRepo.save(user);
    }

    @Transactional
    public User update(Long id, UserDto userDto) {
        final User user = findById(id);
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return userRepo.save(user);
    }

    @Transactional
    public void delete(Long id) {
        //userRepo.delete(findById(id));
        userRepo.deleteUserById(id);
    }
}
