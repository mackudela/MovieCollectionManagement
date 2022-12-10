package pl.polsl.moviecollectionmanagement.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.entities.Permission;
import pl.polsl.moviecollectionmanagement.repositories.PermissionRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public Page<Permission> findAll(Pageable pageable){
        return permissionRepository.findAll(pageable);
    }

    @Transactional
    public Permission create(Permission permission){
        return permissionRepository.save(permission);
    }
}
