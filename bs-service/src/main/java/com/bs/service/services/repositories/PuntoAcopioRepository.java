package com.bs.service.services.repositories;

import com.bs.domain.model.entities.PuntoAcopio;
import com.bs.domain.model.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuntoAcopioRepository extends JpaRepository<PuntoAcopio, Integer> {
    
}
