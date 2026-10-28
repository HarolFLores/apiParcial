package com.example.apiparcial.service.impl;

import com.example.apiparcial.entity.Denuncias;
import com.example.apiparcial.repository.DenunciasRepository;
import com.example.apiparcial.service.DenunciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DenunciasServiceImpl implements DenunciasService {
    @Autowired
    private DenunciasRepository denunciaRepository;

    @Override
    public Denuncias registrar(Denuncias denuncia) {
        denuncia.setEstado(1);
        return denunciaRepository.save(denuncia);
    }

    @Override
    public List<Denuncias> listarPaginado(int offset, int limit) {
        int pageNumber = (limit > 0) ? (offset / limit) : 0;
        return denunciaRepository.findAll(PageRequest.of(pageNumber, limit)).getContent();
    }

    @Override
    public Optional<Denuncias> obtenerPorId(Integer id) {
        return denunciaRepository.findById(Long.valueOf((id)));
    }

    @Override
    public List<Denuncias> obtenerPorDni(String dni) {
        return denunciaRepository.findByDni(dni);
    }

    @Override
    public Denuncias atenderDenuncia(Integer id) {
        return denunciaRepository.findById(Long.valueOf(id)).map(denuncia -> {
            denuncia.setEstado(0);
            return denunciaRepository.save(denuncia);
        }).orElse(null);
    }
}
