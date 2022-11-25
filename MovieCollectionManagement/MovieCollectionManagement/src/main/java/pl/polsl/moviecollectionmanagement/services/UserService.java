package pl.polsl.moviecollectionmanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User addUser(UserDto userDto) {
        final User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return userRepo.save(user);
    }
}
