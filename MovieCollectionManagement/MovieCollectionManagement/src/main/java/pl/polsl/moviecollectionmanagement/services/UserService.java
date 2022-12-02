package pl.polsl.moviecollectionmanagement.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.User;
import pl.polsl.moviecollectionmanagement.repositories.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findUserByLogin(login);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", login);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), authorities);
    }

//    @Autowired
//    public UserService(UserRepository userRepo){
//        this.userRepo = userRepo;
//    }

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

    public User findByLogin(String login) {
        return userRepo.findUserByLogin(login);
    }

    public UserDto getDto(Long id) {
        final User user = findById(id);
        return new UserDto(user);
    }

    @Transactional
    public User create(UserDto userDto) {
        final User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
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
