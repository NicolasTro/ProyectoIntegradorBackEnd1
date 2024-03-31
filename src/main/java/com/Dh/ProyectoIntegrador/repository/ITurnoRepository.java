package com.Dh.ProyectoIntegrador.repository;

import com.Dh.ProyectoIntegrador.entity.Odontologo;
import com.Dh.ProyectoIntegrador.entity.Turno;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {

    // Consulta HQL para buscar turnos por los pacientes agendados
@Query("SELECT t FROM Turno t WHERE LOWER(t.paciente.nombre) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(t.paciente.apellido) LIKE LOWER(CONCAT('%', ?1, '%'))")
    Optional<List<Turno>> findByPacientes(String valor);
    // Consulta HQL para buscar turnos por los odontologos agendados
    @Query("SELECT t FROM Turno t WHERE LOWER(t.odontologo.nombre) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(t.odontologo.apellido) LIKE LOWER(CONCAT('%', ?1, '%'))")
    Optional<List<Turno>> findByOdontologo(String valor);
    // Consulta HQL para encontrar los turnos asociados a una fecha especifica.
    @Query("SELECT t FROM Turno t WHERE t.fechaYHora = :valor")
    Optional<List<Turno>> findByFecha(LocalDateTime valor);
}
