package com.example.apiBloqueoCalendario.service;

import com.example.apiBloqueoCalendario.exception.ConflictoException;
import com.example.apiBloqueoCalendario.exception.RecursoNoEncontradoException;
import com.example.apiBloqueoCalendario.exception.ReglaNegocioException;
import com.example.apiBloqueoCalendario.model.BloqueoCalendario;
import com.example.apiBloqueoCalendario.repository.BloqueoCalendarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloqueoCalendarioService {

    private final BloqueoCalendarioRepository repository;

    public BloqueoCalendarioService(BloqueoCalendarioRepository repository) {
        this.repository = repository;
    }

    public List<BloqueoCalendario> listar() {
        return repository.findAll();
    }

    public BloqueoCalendario obtener(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Bloqueo no encontrado: " + id));
    }

    public BloqueoCalendario crear(BloqueoCalendario bloq) {
        bloq.setId(null);//hace q no se pueda duplicar a la hora de hacer post
        validar(bloq);
        if (repository.existsByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(
                bloq.getFechaFin(), bloq.getFechaInicio())) {
            throw new ConflictoException("Ya existe un tramo en esas fechas");
        }
        return repository.save(bloq);
    }

    public BloqueoCalendario actualizar(Long id, BloqueoCalendario datos) {
        BloqueoCalendario existente = obtener(id);

        existente.setFechaInicio(datos.getFechaInicio());
        existente.setFechaFin(datos.getFechaFin());
        existente.setTipo(datos.getTipo());

        validar(existente);

        if (repository.existsByFechaInicioLessThanEqualAndFechaFinGreaterThanEqualAndIdNot(
                existente.getFechaFin(), existente.getFechaInicio(), id)) {
            throw new ConflictoException("Ya existe un tramo en esas fechas");
        }
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("Bloqueo no encontrado: " + id);
        }
        repository.deleteById(id);
    }

    private void validar(BloqueoCalendario bloq) {
        if (bloq.getFechaInicio().isAfter(bloq.getFechaFin())) {
            throw new ReglaNegocioException("No se puede crear una fecha de fin que vaya antes que la de inicio");
        }
    }
}