package pl.polsl.moviecollectionmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.moviecollectionmanagement.entities.Permission;
import pl.polsl.moviecollectionmanagement.entities.Role;
import pl.polsl.moviecollectionmanagement.enums.PermissionName;
import pl.polsl.moviecollectionmanagement.enums.RoleName;
import pl.polsl.moviecollectionmanagement.services.PermissionService;
import pl.polsl.moviecollectionmanagement.services.RoleService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final PermissionService permissionService;
    private final RoleService roleService;

    @GetMapping("/permissions/all")
    public ResponseEntity<Page<Permission>> findAllPermissions(@PageableDefault Pageable pageable) {
        Pageable wholePage = Pageable.unpaged();
        Page<Permission> permissions = permissionService.findAll(wholePage);
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @PostMapping("/permissions/create")
    public void createPermission() {
        Permission permission1 = new Permission();
        permission1.setName(PermissionName.CREATE_USER);

        Permission permission2 = new Permission();
        permission2.setName(PermissionName.FIND_USER);

        Permission permission3 = new Permission();
        permission3.setName(PermissionName.UPDATE_USER);

        Permission permission4 = new Permission();
        permission4.setName(PermissionName.DELETE_USER);

        Permission permission5 = new Permission();
        permission5.setName(PermissionName.CREATE_MOVIE);

        Permission permission6 = new Permission();
        permission6.setName(PermissionName.FIND_MOVIE);

        Permission permission7 = new Permission();
        permission7.setName(PermissionName.UPDATE_MOVIE);

        Permission permission8 = new Permission();
        permission8.setName(PermissionName.DELETE_MOVIE);

        Permission permission9 = new Permission();
        permission9.setName(PermissionName.CREATE_REVIEW);

        Permission permission10 = new Permission();
        permission10.setName(PermissionName.FIND_REVIEW);

        Permission permission11 = new Permission();
        permission11.setName(PermissionName.UPDATE_REVIEW);

        Permission permission12 = new Permission();
        permission12.setName(PermissionName.DELETE_REVIEW);

        Permission permission13 = new Permission();
        permission13.setName(PermissionName.CREATE_FORUMPOST);

        Permission permission14 = new Permission();
        permission14.setName(PermissionName.FIND_FORUMPOST);

        Permission permission15 = new Permission();
        permission15.setName(PermissionName.UPDATE_FORUMPOST);

        Permission permission16 = new Permission();
        permission16.setName(PermissionName.DELETE_FORUMPOST);


        Role role1 = new Role();
        role1.setName(RoleName.USER);
        role1.getPermissions().add(permission6);
        permission6.getRoles().add(role1);
        role1.getPermissions().add(permission9);
        permission9.getRoles().add(role1);
        role1.getPermissions().add(permission10);
        permission10.getRoles().add(role1);
        role1.getPermissions().add(permission11);
        permission11.getRoles().add(role1);
        role1.getPermissions().add(permission13);
        permission13.getRoles().add(role1);
        role1.getPermissions().add(permission14);
        permission14.getRoles().add(role1);
        role1.getPermissions().add(permission15);
        permission15.getRoles().add(role1);


        Role role2 = new Role();
        role2.setName(RoleName.ADMIN);
        role2.getPermissions().add(permission1);
        permission1.getRoles().add(role2);
        role2.getPermissions().add(permission2);
        permission2.getRoles().add(role2);
        role2.getPermissions().add(permission3);
        permission3.getRoles().add(role2);
        role2.getPermissions().add(permission4);
        permission4.getRoles().add(role2);
        role2.getPermissions().add(permission5);
        permission5.getRoles().add(role2);
        role2.getPermissions().add(permission6);
        permission6.getRoles().add(role2);
        role2.getPermissions().add(permission7);
        permission7.getRoles().add(role2);
        role2.getPermissions().add(permission8);
        permission8.getRoles().add(role2);
        role2.getPermissions().add(permission9);
        permission9.getRoles().add(role2);
        role2.getPermissions().add(permission10);
        permission10.getRoles().add(role2);
        role2.getPermissions().add(permission11);
        permission11.getRoles().add(role2);
        role2.getPermissions().add(permission12);
        permission12.getRoles().add(role2);
        role2.getPermissions().add(permission13);
        permission13.getRoles().add(role2);
        role2.getPermissions().add(permission14);
        permission14.getRoles().add(role2);
        role2.getPermissions().add(permission15);
        permission15.getRoles().add(role2);
        role2.getPermissions().add(permission16);
        permission16.getRoles().add(role2);

        permissionService.create(permission1);
        permissionService.create(permission2);
        permissionService.create(permission3);
        permissionService.create(permission4);
        permissionService.create(permission5);
        permissionService.create(permission6);
        permissionService.create(permission7);
        permissionService.create(permission8);
        permissionService.create(permission9);
        permissionService.create(permission10);
        permissionService.create(permission11);
        permissionService.create(permission12);
        permissionService.create(permission13);
        permissionService.create(permission14);
        permissionService.create(permission15);
        permissionService.create(permission16);

        roleService.create(role1);
        roleService.create(role2);
    }
}
