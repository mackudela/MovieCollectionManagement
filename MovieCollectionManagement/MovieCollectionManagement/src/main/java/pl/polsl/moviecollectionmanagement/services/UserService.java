package pl.polsl.moviecollectionmanagement.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.Role;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.enums.RoleName;
import pl.polsl.moviecollectionmanagement.repositories.MovieRepository;
import pl.polsl.moviecollectionmanagement.repositories.RoleRepository;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Set;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepository;

    //private final MovieRepository movieRepository;

    public Page<UserDto> findAll(Pageable pageable) {
        final Page<User> users = userRepo.findAll(pageable);
        return users.map(UserDto::new);
    }

    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " does not exist"));
    }

//    public User findByLogin(String login) {
//        return userRepo.findUserByLogin(login);
//    }

    public UserDto getDto(Long id) {
        final User user = findById(id);
        return new UserDto(user);
    }

    @Transactional
    public User create(UserDto userDto) {
        final User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());

        Set<Role> roles = user.getRoles();

        Role role = roleRepository.findByName(RoleName.USER);
        role.getUsers().add(user);
        roles.add(role);

//        Role role2 = roleRepository.findByName(RoleName.ADMIN);
//        role2.getUsers().add(user);
//        roles.add(role2);

        user.setRoles(roles);
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
        userRepo.deleteUserById(id);
    }
}
