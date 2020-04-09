package com.bs.service.services.interfaces;

import com.bs.domain.exception.ControlledException;
import com.bs.domain.model.dto.ws.response.PuntoSolicitud;
import com.bs.domain.model.entities.Solicitud;

import java.util.List;

/**
 * 
 * @author Milton BO
 */
public interface ISolicitudService {

    List<PuntoSolicitud> obtenerListaPuntosSolicitudes();

    Solicitud obtenerDetalleSolicitud(Integer id) throws ControlledException;
}
