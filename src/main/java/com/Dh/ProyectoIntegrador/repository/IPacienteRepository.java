package com.Dh.ProyectoIntegrador.repository;

import com.Dh.ProyectoIntegrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nombre) LIKE LOWER(concat('%', ?1, '%'))")
    Optional<List<Paciente>> findByNombre(String valor);
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.apellido) LIKE LOWER(concat('%', ?1, '%'))")
    Optional<List<Paciente>> findByApellido(String valor);
    @Query("SELECT p FROM Paciente p WHERE p.fechaIngreso =?1")
    Optional<List<Paciente>> findByFecha(Date valor);


}
