package com.example.biblio_security.service.general.impl;


import com.example.biblio_security.dto.CategoriaDTO;
import com.example.biblio_security.entity.Categoria;
import com.example.biblio_security.mappers.CategoriaMapper;
import com.example.biblio_security.repository.CategoriaRepository;
import com.example.biblio_security.service.general.service.CategoriaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaDTO> findAll() throws ServiceException {
        List<Categoria> list = categoriaRepository.findAll();
        return list.stream().map(l->categoriaMapper.toDto(l)).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriaDTO> findById(long id) throws ServiceException {
        return categoriaRepository.findById(id).map(l->categoriaMapper.toDto(l));
    }

    @Override
    public List<CategoriaDTO> findByObject(CategoriaDTO categoriaDTO) throws ServiceException {
        return List.of();
    }

    @Override
    public CategoriaDTO save(CategoriaDTO categoriaDTO) throws ServiceException {
        return categoriaMapper.toDto(categoriaRepository.save(categoriaMapper.toEntity(categoriaDTO)));
    }

    @Override
    public CategoriaDTO update(CategoriaDTO categoriaDTO) throws ServiceException {
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaDTO.getId());
        if (categoria.isPresent()) {
            Categoria cat = new Categoria();
            cat.setNombre(categoriaDTO.getNombre());
            cat.setId(categoriaDTO.getId());
            return categoriaMapper.toDto(categoriaRepository.save(cat));
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteLogic(Long id) throws ServiceException {
        try {
            Categoria categoria = categoriaRepository.findById(id)
                    .orElseThrow(() -> new ServiceException("La categoría con id " + id + " no existe"));
            categoriaRepository.save(categoria);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al intentar eliminar la categoría con id " + id, e);
        }
    }
}