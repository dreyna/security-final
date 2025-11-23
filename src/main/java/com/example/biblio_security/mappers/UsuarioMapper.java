package com.example.biblio_security.mappers;
import com.example.biblio_security.dto.UsuarioDTO;
import com.example.biblio_security.entity.security.Usuario;
import com.example.biblio_security.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

    @Override
    @Mapping(target = "rol", expression = "java(entity.getRol().getNombre())")
    UsuarioDTO toDTO(Usuario entity);

    @Override
    @Mapping(target = "rol", ignore = true)  // se asigna en el servicio
    Usuario toEntity(UsuarioDTO dto);
}

