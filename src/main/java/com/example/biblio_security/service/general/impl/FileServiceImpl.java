package com.example.biblio_security.service.general.impl;

import com.example.biblio_security.entity.Archivo;
import com.example.biblio_security.repository.ArchivoRepository;
import com.example.biblio_security.service.general.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ArchivoRepository archivoRepository;

    @Override
    public List<Archivo> saveFiles(List<MultipartFile> archivos) throws IOException {
        List<Archivo> lista = new ArrayList<>();

        File carpeta = new File(uploadPath);
        if (!carpeta.exists()) carpeta.mkdirs();

        for (MultipartFile file : archivos) {

            // Obtener extensión del archivo (si existe)
            String nombreOriginal = file.getOriginalFilename();
            String extension = "";

            if (nombreOriginal != null && nombreOriginal.contains(".")) {
                extension = nombreOriginal.substring(nombreOriginal.lastIndexOf("."));
            }

            // Generar nombre único con la extensión original
            String nombreGuardado = UUID.randomUUID().toString() + extension;

            Path ruta = Paths.get(uploadPath + nombreGuardado);
            Files.copy(file.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            Archivo a = new Archivo();
            a.setNombreOriginal(nombreOriginal);
            a.setNombreGuardado(nombreGuardado);
            a.setRuta(ruta.toString());
            a.setTamanio(file.getSize());
            a.setFechaSubida(new Date());

            archivoRepository.save(a);
            lista.add(a);
        }

        return lista;
    }

    @Override
    public Resource download(Long id) {
        Archivo archivo = archivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        return new FileSystemResource(archivo.getRuta());
    }

    @Override
    public boolean delete(Long id) {
        Archivo archivo = archivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo no encontrado"));

        File f = new File(archivo.getRuta());
        if (f.exists()) f.delete();

        archivoRepository.deleteById(id);
        return true;
    }
}

