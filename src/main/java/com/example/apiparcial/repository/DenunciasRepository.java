package com.example.apiparcial.repository;

import com.example.apiparcial.entity.Denuncias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DenunciasRepository extends JpaRepository<Denuncias, Integer> {
    List<Denuncias> findByDni(String dni);
}