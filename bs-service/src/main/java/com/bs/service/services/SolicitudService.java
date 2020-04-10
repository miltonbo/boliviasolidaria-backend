package com.bs.service.services;

import com.bs.domain.exception.ControlledException;
import com.bs.domain.model.dto.ws.response.PuntoSolicitud;
import com.bs.domain.model.entities.Solicitud;
import com.bs.domain.types.EstadoEnum;
import com.bs.domain.utils.DateUtil;
import com.bs.domain.utils.HardCodeUtil;
import com.bs.domain.utils.StringUtil;
import com.bs.service.services.interfaces.ISolicitudService;
import com.bs.service.services.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public Solicitud registrarSolicitud(Solicitud solicitud) throws ControlledException {
        String validacion = validar(solicitud);
        if (validacion != null)
            throw new ControlledException(validacion);
        solicitud.setId(null);
        solicitud.setFechahoraSolicitud(new Date());
        solicitud.setEstado(EstadoEnum.ENABLED.getId());
        return solicitudRepository.save(solicitud);
    }

    private String validar(Solicitud solicitud) {
        if (StringUtil.isEmpty(solicitud.getCi()) || solicitud.getCi().length() < 4
                || solicitud.getCi().length() > 10)
            return "El documento de identidad es invalido. Debe no estar vacio ni superar los 10 caracteres.";
        if (StringUtil.isEmpty(solicitud.getContacto()) || solicitud.getContacto().length() < 5
                || solicitud.getContacto().length() > 20)
            return "El numero de contacto es invalido, no puede ser vacio ni superar los 20 caracteres.";
        if (StringUtil.isEmpty(solicitud.getDireccion()) || solicitud.getDireccion().length() < 5
                || solicitud.getDireccion().length() > 255)
            return "La direccion no puede ser vacio ni tampoco superar los 255 caracteres.";
        if (StringUtil.isEmpty(solicitud.getNombre()) || solicitud.getNombre().length() < 5
                || solicitud.getNombre().length() > 255)
            return "Su nombre no puede estar vacio, ni superar los 255 caracteres.";
        if (solicitud.getLat() == null || solicitud.getLng() == null
                || solicitud.getLat() == 0 || solicitud.getLng() == 0)
            return "Debe registrar su ubicacion GPS en la solicitud.";
        Date limiteFechaHora = DateUtil.getDateAddDays(new Date(),
                -1 * HardCodeUtil.MAXIMO_DIAS_ATRAS_SOLICITUDES_VIGENTES);
        List<Solicitud> solicitudes = solicitudRepository.findByEstadoAndCI(
                EstadoEnum.ENABLED.getId(), solicitud.getCi(), limiteFechaHora);
        if (solicitudes.size() > 0)
            return "Usted ya tiene una solicitud en proceso, por favor espere la ayuda.";
        return null;
    }

}
