package com.example.biblio_security.service.general.service;

import com.example.biblio_security.entity.Archivo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<Archivo> saveFiles(List<MultipartFile> archivos) throws IOException;
    Resource download(Long id);
    boolean delete(Long id);
}