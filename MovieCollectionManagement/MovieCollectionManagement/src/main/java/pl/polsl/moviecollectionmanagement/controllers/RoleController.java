package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.polsl.moviecollectionmanagement.dtos.UserDto;
import pl.polsl.moviecollectionmanagement.entities.Permission;
import pl.polsl.moviecollectionmanagement.services.PermissionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final PermissionService permissionService;

    @GetMapping("/permissions/all")
    public ResponseEntity<Page<Permission>> findAllPermissions(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<Permission> permissions = permissionService.findAll(wholePage);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PostMapping("/permissions/create")
    public ResponseEntity<Long> createPermission(@RequestBody Permission permission) {
        return new ResponseEntity<>(permissionService.create(permission).getId(), HttpStatus.CREATED);
    }
}
