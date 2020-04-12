package com.bs.api;

import com.bs.domain.model.entities.Solicitud;
import com.bs.domain.types.EstadoEnum;
import com.bs.domain.utils.DateUtil;
import com.bs.domain.utils.HardCodeUtil;
import com.bs.service.services.interfaces.ISolicitudService;
import com.bs.service.services.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableScheduling
public class Procesos {

    private static final Logger log = Logger.getLogger(Procesos.class.getName());

    @Autowired
    private ISolicitudService solicitudService;

    @Autowired
    private SolicitudRepository solicitudRepository;


//    @Scheduled(fixedDelay = 15000L)
    @Scheduled(fixedDelay = 3600000L)
    protected void procesar() {
        procesarSolicitudesExpiradas();
    }

    private void procesarSolicitudesExpiradas() {
        try {
            log.info("Iniciando proceso de expirados::::");
            Date limiteFechaHora = DateUtil.getDateAddDays(new Date(),
                    -1 * HardCodeUtil.MAXIMO_DIAS_ATRAS_SOLICITUDES_VIGENTES);
            List<Solicitud> solicitudes = solicitudRepository.findByEstadoAndDateMoreThan(
                    EstadoEnum.ENABLED.getId(), limiteFechaHora);
            solicitudes.forEach(solicitud -> {
                solicitud.setEstado(EstadoEnum.EXPIRADO.getId());
                log.info("Expirando la solicitud: " + solicitud.getId());
                solicitudRepository.save(solicitud);
            });
            log.info("Cantidad de solicitudes expiradas: " + solicitudes.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al procesar los expirados", e);
        }
    }

}
