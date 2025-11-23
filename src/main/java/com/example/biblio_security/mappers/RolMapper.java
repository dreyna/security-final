package com.example.biblio_security.mappers;

import com.example.biblio_security.dto.RolDTO;
import com.example.biblio_security.entity.security.Rol;
import com.example.biblio_security.mappers.base.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper extends BaseMapper<Rol, RolDTO> {
}
