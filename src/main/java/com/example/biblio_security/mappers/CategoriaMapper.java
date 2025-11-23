package com.example.biblio_security.mappers;

import com.example.biblio_security.dto.CategoriaDTO;
import com.example.biblio_security.entity.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDto(Categoria categoria);
    Categoria toEntity(CategoriaDTO dto);
    List<CategoriaDTO> toDtoList(List<Categoria> categorias);
}
