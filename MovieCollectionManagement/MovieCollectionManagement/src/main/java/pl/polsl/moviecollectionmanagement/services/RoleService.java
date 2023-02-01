package pl.polsl.moviecollectionmanagement.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.polsl.moviecollectionmanagement.entities.Role;
import pl.polsl.moviecollectionmanagement.repositories.RoleRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;


    public Page<Role> findAll(Pageable pageable){
        return roleRepository.findAll(pageable);
    }

    @Transactional
    public Role create(Role role){
        return roleRepository.save(role);
    }
}
