package com.bs.service.services;

import com.bs.domain.exception.ControlledException;
import com.bs.domain.model.dto.ws.response.PuntoSolicitud;
import com.bs.domain.model.entities.Solicitud;
import com.bs.domain.types.EstadoEnum;
import com.bs.service.services.interfaces.ISolicitudService;
import com.bs.service.services.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 
 * @author Milton BO
 */
@Service
public class SolicitudService implements ISolicitudService {

    private static final Logger log = Logger.getLogger(SolicitudService.class.getName());

    @Autowired
    private SolicitudRepository solicitudRepository;


    @Override
    public List<PuntoSolicitud> obtenerListaPuntosSolicitudes() {
        List<Solicitud>  solicitudes = solicitudRepository.findByEstado(EstadoEnum.ENABLED.getId());
        return solicitudes.stream().map(
                solicitud -> new PuntoSolicitud(solicitud.getId(), solicitud.getLat(), solicitud.getLng())
        ).collect(Collectors.toList());
    }

    @Override
    public Solicitud obtenerDetalleSolicitud(Integer id) throws ControlledException {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
        if (solicitudOptional.isPresent())
            return solicitudOptional.get();
        throw new ControlledException("Solicitud no encontrada");
    }
}
