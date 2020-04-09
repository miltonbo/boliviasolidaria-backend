package com.bs.api.endpoints;

import com.bs.service.services.interfaces.ISolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        return solicitudService.obtenerListaPuntosSolicitudes();
    }
    
}
