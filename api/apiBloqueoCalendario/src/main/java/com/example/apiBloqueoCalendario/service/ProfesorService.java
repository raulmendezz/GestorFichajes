package com.example.apiBloqueoCalendario.service;

import com.example.apiBloqueoCalendario.exception.ConflictoException;
import com.example.apiBloqueoCalendario.exception.RecursoNoEncontradoException;
import com.example.apiBloqueoCalendario.model.Profesor;
import com.example.apiBloqueoCalendario.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public List<Profesor> listar() {
        return repository.findAll();
    }

    public Profesor obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Profesor no encontrado: " + id));
    }

    public Profesor crear(Profesor p) {
        p.setId(null);
        if (repository.existsByDni(p.getDni())) {
            throw new ConflictoException("Ya existe un profesor con ese DNI");
        }

        return repository.save(p);
    }

    public Profesor actualizar(Long id, Profesor datos) {
        Profesor existente = obtener(id);

        if (repository.existsByDniAndIdNot(datos.getDni(), id)) {
            throw new ConflictoException("Ya existe un profesor con ese DNI");
        }

        existente.setDni(datos.getDni());
        existente.setNombre(datos.getNombre());
        existente.setApellidos(datos.getApellidos());
        existente.setCp(datos.getCp());
        existente.setLocalidad(datos.getLocalidad());
        existente.setTelefono(datos.getTelefono());
        existente.setDireccion(datos.getDireccion());
        existente.setCuerpo(datos.getCuerpo());
        existente.setEspecialidad(datos.getEspecialidad());
        existente.setDepartamento(datos.getDepartamento());

        return repository.save(existente);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNoEncontradoException("Profesor no encontrado: " + id);
        }
        repository.deleteById(id);
    }
}