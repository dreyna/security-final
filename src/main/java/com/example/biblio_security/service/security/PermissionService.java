package com.example.biblio_security.service.security;

import com.example.biblio_security.entity.security.Usuario;
import com.example.biblio_security.repository.security.GrantedPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private GrantedPermissionRepository grantedPermissionRepository;

    public List<String> getOperationNamesByUser(Usuario user) {
        return grantedPermissionRepository.findByRolId(user.getRol().getId())
                .stream()
                .map(gp -> gp.getOperacion().getNombre())
                .toList();
    }
}