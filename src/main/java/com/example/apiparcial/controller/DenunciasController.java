package com.example.apiparcial.controller;

import com.example.apiparcial.entity.Denuncias;
import com.example.apiparcial.service.DenunciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/denuncias")
public class DenunciasController {
    @Autowired
    private DenunciasService service;

    @PostMapping("/")
    public ResponseEntity<Denuncias> registrar(@RequestBody Denuncias denuncia) {
        Denuncias nueva = service.registrar(denuncia);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Denuncias>> obtenerPaginadas(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "5") int limit) {
        List<Denuncias> lista = service.listarPaginado(offset, limit);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncias> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/ciudadano/{dni}")
    public ResponseEntity<List<Denuncias>> obtenerPorCiudadano(@PathVariable String dni) {
        List<Denuncias> lista = service.obtenerPorDni(dni);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncias> atender(@PathVariable Integer id) {
        Denuncias atendida = service.atenderDenuncia(id);
        if (atendida != null) {
            return ResponseEntity.ok(atendida);
        }
        return ResponseEntity.notFound().build();
    }
}
