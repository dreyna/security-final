package com.example.biblio_security.repository.security;

import com.example.biblio_security.entity.security.GrantedPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrantedPermissionRepository extends JpaRepository<GrantedPermission, Long> {

    List<GrantedPermission> findByRolId(Long rolId);
}