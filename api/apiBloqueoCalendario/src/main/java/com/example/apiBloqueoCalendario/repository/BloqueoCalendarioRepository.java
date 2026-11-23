package com.example.apiBloqueoCalendario.repository;

import com.example.apiBloqueoCalendario.model.BloqueoCalendario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;


public interface BloqueoCalendarioRepository extends JpaRepository<BloqueoCalendario, Long> {
    boolean existsByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(LocalDate fin, LocalDate inicio);

    boolean existsByFechaInicioLessThanEqualAndFechaFinGreaterThanEqualAndIdNot(LocalDate fin, LocalDate inicio, Long id);
}