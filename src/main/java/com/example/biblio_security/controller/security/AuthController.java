package com.example.biblio_security.controller.security;

import com.example.biblio_security.dto.security.JwtResponse;
import com.example.biblio_security.dto.security.LoginRequest;
import com.example.biblio_security.entity.security.Usuario;
import com.example.biblio_security.repository.security.UsuarioRepository;
import com.example.biblio_security.service.security.JwtService;
import com.example.biblio_security.service.security.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        List<String> operations = permissionService.getOperationNamesByUser(usuario);
        String token = jwtService.generateToken(
                userDetails,
                usuario,
                operations
        );

        return ResponseEntity.ok(new JwtResponse(token));
    }
}