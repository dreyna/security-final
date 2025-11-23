package com.example.biblio_security.dto;
import lombok.Data;
import java.util.Set;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    private String rol;
}
