package com.Dh.ProyectoIntegrador.repository;

import com.Dh.ProyectoIntegrador.entity.Domicilio;
import com.Dh.ProyectoIntegrador.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    
}
