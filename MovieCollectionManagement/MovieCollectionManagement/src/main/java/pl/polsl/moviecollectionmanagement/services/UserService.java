package pl.polsl.moviecollectionmanagement.services;

import org.springframework.stereotype.Service;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }
}
