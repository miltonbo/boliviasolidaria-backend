package com.bs.service.services.interfaces;

import com.bs.domain.model.dto.ws.response.PuntoSolicitud;

import java.util.List;

/**
 * 
 * @author Milton BO
 */
public interface ISolicitudService {

    List<PuntoSolicitud> obtenerListaPuntosSolicitudes();

}
