package com.bs.api.endpoints;

import com.bs.domain.exception.ControlledException;
import com.bs.service.services.interfaces.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 *
 * @author Milton BO
 */
@RestController
@RequestMapping("/solicitudes")
class SolicitudEndpoint {

    private static final Logger log = Logger.getLogger(SolicitudEndpoint.class.getName());
    
    @Autowired
    private ISolicitudService solicitudService;

    @GetMapping(value = "/puntos")
    private Object obtenerListaPuntosSolicitudes() {
        log.info("Obteniendo la lista de puntos de solicitudes");
        return solicitudService.obtenerListaPuntosSolicitudes();
    }

    @GetMapping(value = "{id}")
    private Object obtenerDetalleSolicitud(@PathVariable Integer id) {
        try {
            log.info("Obteniendo detalle de una solicitud registrada, id: " + id);
            return solicitudService.obtenerDetalleSolicitud(id);
        } catch (ControlledException ce) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
