package com.Dh.ProyectoIntegrador.repository;

import com.Dh.ProyectoIntegrador.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {

    // Consulta HQL para buscar odontólogos por nombre
    @Query("SELECT o FROM Odontologo o WHERE LOWER(o.nombre) LIKE LOWER(concat('%', ?1, '%'))")
    Optional<List<Odontologo>> findByNombre(String valor);

    // Consulta HQL para buscar odontólogos por apellido
    @Query("SELECT o FROM Odontologo o WHERE LOWER(o.apellido) LIKE LOWER(concat('%', ?1, '%'))")
    Optional<List<Odontologo>> findByApellido(String valor);

    // Consulta HQL para buscar odontólogos por matrícula
    @Query("SELECT o FROM Odontologo o WHERE LOWER(o.matricula) LIKE LOWER(concat('%', ?1, '%'))")
    Optional<List<Odontologo>> findByMatricula(String valor);

    // Consulta HQL para contar los turnos asociados a un odontólogo específico
    @Query("SELECT COUNT(t) FROM Turno t WHERE t.odontologo.id = :id")
    Long findByOdontologoTurno(Long id);
}
