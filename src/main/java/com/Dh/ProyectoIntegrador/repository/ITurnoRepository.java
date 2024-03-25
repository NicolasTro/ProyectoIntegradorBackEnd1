package com.Dh.ProyectoIntegrador.repository;

import com.Dh.ProyectoIntegrador.entity.Odontologo;
import com.Dh.ProyectoIntegrador.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    @Query("SELECT t FROM Turno t WHERE t.paciente.id =?1")
    Optional<List<Turno>> findByPacientes(String valor);
    @Query("SELECT t FROM Turno t WHERE t.odontologo.id =?1")
    Optional<List<Turno>> findByOdontologo(String valor);
    @Query("SELECT t FROM Turno t WHERE DATE_TRUNC('second', t.fechaYHora) = DATE_TRUNC('second', ?1)")
    Optional<List<Turno>> findByFecha(String valor);
}
