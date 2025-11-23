package com.example.biblio_security.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="categoria")
public class Categoria {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id=0L;

    @Column(name = "NOMBRE", unique = true,  nullable = false,  length = 20)
    @Size(min = 5, max = 20)
    private String nombre;

}