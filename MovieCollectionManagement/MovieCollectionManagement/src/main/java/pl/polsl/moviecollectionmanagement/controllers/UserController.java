package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.services.UserService;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('FIND_USER')")
    @GetMapping("/all")
    public ResponseEntity<Page<UserDto>> findAllUsers(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<UserDto> users = userService.findAll(wholePage);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable("id") Long id) {
        UserDto userDto = userService.getDto(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto).getId(), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_USER')")
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        userService.update(userDto.getId(), userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
