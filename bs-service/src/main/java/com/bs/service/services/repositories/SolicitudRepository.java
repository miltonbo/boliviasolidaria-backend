package com.bs.service.services.repositories;

import com.bs.domain.model.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    @Query("SELECT t FROM Solicitud t WHERE t.estado = :estado")
    List<Solicitud> findByEstado(@Param("estado") byte estado);

    @Query("SELECT t FROM Solicitud t WHERE t.estado = :estado " +
            "AND t.ci = :ci " +
            "AND t.fechahoraSolicitud > :limiteFechaHora")
    List<Solicitud> findByEstadoAndCI(@Param("estado") byte estado,
                                      @Param("ci") String ci,
                                      @Param("limiteFechaHora") Date limiteFechaHora);

    @Query("SELECT t FROM Solicitud t WHERE t.estado = :estado " +
            "AND t.fechahoraSolicitud < :limiteFechaHora")
    List<Solicitud> findByEstadoAndDateMoreThan(@Param("estado") byte estado,
                                      @Param("limiteFechaHora") Date limiteFechaHora);
    
}
