package com.example.apiparcial.service;

import com.example.apiparcial.entity.Denuncias;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface DenunciasService {
    Denuncias registrar(Denuncias denuncia);
    List<Denuncias> listarPaginado(int offset, int limit);
    Optional<Denuncias> obtenerPorId(Integer id);
    List<Denuncias> obtenerPorDni(String dni);
    Denuncias atenderDenuncia(Integer id);
}
