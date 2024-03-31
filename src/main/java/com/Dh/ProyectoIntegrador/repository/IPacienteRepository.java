package com.Dh.ProyectoIntegrador.repository;

import com.Dh.ProyectoIntegrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    // Consulta HQL para buscar pacientes por nombre
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE LOWER(concat('%', ?1, '%'))")
    Optional<List<Paciente>> findByNombre(String valor);
    // Consulta HQL para buscar pacientes por apellido
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.apellido) LIKE LOWER(concat('%', ?1, '%'))")
    Optional<List<Paciente>> findByApellido(String valor);
    // Consulta HQL para buscar paciente por la fecha de ingreso
    @Query("SELECT p FROM Paciente p WHERE p.fechaIngreso =?1")
    Optional<List<Paciente>> findByFecha(LocalDate valor);

    // Consulta HQL para contar los turnos asociados a un paciente específico
    @Query("SELECT COUNT(t) FROM Turno t WHERE t.paciente.id = :id")
    Long findByPacienteTurno(Long id);

}
