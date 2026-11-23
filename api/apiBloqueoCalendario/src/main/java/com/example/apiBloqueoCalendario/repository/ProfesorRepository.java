package com.example.apiBloqueoCalendario.repository;

import com.example.apiBloqueoCalendario.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor,Long> {
    boolean existsByDni(String dni);
    boolean existsByDniAndIdNot(String dni,Long id);
}
