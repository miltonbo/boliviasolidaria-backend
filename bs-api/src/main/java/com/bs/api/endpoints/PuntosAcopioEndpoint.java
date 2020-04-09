package com.bs.api.endpoints;

import com.bs.domain.exception.ControlledException;
import com.bs.domain.model.entities.Solicitud;
import com.bs.domain.utils.HardCodeUtil;
import com.bs.domain.utils.ImageUtils;
import com.bs.domain.utils.SystemPropertyUtil;
import com.bs.service.services.interfaces.IPuntoAcopioService;
import com.bs.service.services.interfaces.ISolicitudService;
import com.bs.service.services.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milton BO
 */
@RestController
@RequestMapping("/puntos-acopio")
class PuntosAcopioEndpoint {

    private static final Logger log = Logger.getLogger(PuntosAcopioEndpoint.class.getName());
    
    @Autowired
    private IPuntoAcopioService puntoAcopioService;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @GetMapping(value = "/puntos")
    private Object obtenerListaPuntosDeAcopio() {
        log.info("Obteniendo la lista de puntos de acopio");
        return puntoAcopioService.obtenerListaPuntosDeAcopio();
    }

}
