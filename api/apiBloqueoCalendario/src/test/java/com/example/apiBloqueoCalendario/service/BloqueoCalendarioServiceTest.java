package com.example.apiBloqueoCalendario.service;

import com.example.apiBloqueoCalendario.exception.ConflictoException;
import com.example.apiBloqueoCalendario.exception.RecursoNoEncontradoException;
import com.example.apiBloqueoCalendario.exception.ReglaNegocioException;
import com.example.apiBloqueoCalendario.model.BloqueoCalendario;
import com.example.apiBloqueoCalendario.repository.BloqueoCalendarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BloqueoCalendarioServiceTest {

    @Mock
    private BloqueoCalendarioRepository repository;

    @InjectMocks
    private BloqueoCalendarioService service;

    private BloqueoCalendario crearBloqueo(LocalDate inicio, LocalDate fin) {
        BloqueoCalendario b = new BloqueoCalendario();
        b.setFechaInicio(inicio);
        b.setFechaFin(fin);
        b.setTipo("Festivo");
        return b;
    }

    @Test
    void crear_conFechasInvertidas_lanzaReglaNegocio() {
        BloqueoCalendario b = crearBloqueo(LocalDate.of(2026, 12, 10), LocalDate.of(2026, 12, 1));

        assertThrows(ReglaNegocioException.class, () -> service.crear(b));

        verify(repository, never()).save(any());
    }

    @Test
    void crear_conSolape_lanzaConflicto() {
        BloqueoCalendario b = crearBloqueo(LocalDate.of(2026, 12, 1), LocalDate.of(2026, 12, 10));

        when(repository.existsByFechaInicioLessThanEqualAndFechaFinGreaterThanEqual(any(), any()))
                .thenReturn(true);

        assertThrows(ConflictoException.class, () -> service.crear(b));
        verify(repository, never()).save(any());
    }

    @Test
    void crear_correcto_guardaElBloqueo() {
        BloqueoCalendario b = crearBloqueo(LocalDate.of(2026, 12, 1), LocalDate.of(2026, 12, 5));
        when(repository.save(b)).thenReturn(b);

        BloqueoCalendario resultado = service.crear(b);

        assertEquals("Festivo", resultado.getTipo());
        verify(repository).save(b);
    }

    @Test
    void obtener_idInexistente_lanzaNoEncontrado() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNoEncontradoException.class, () -> service.obtener(99L));
    }
}