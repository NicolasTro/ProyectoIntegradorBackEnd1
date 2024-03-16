package com.Dh.ProyectoIntegrador.repository;

import com.Dh.ProyectoIntegrador.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
