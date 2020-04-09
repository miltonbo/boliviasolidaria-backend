package com.bs.api.endpoints;

import com.bs.domain.exception.ControlledException;
import com.bs.domain.model.entities.Solicitud;
import com.bs.domain.utils.HardCodeUtil;
import com.bs.domain.utils.ImageUtils;
import com.bs.domain.utils.SystemPropertyUtil;
import com.bs.service.services.interfaces.IReceptorDonacionService;
import com.bs.service.services.interfaces.ISolicitudService;
import com.bs.service.services.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping("/receptores-donacion")
class ReceptoresDonacionEndpoint {

    private static final Logger log = Logger.getLogger(ReceptoresDonacionEndpoint.class.getName());
    
    @Autowired
    private IReceptorDonacionService receptorDonacionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private Object obtenerCuentasDeReceptoresDeDonacion() {
        try {
            log.info("Obteniendo la lista de receptores de donacion");
            return receptorDonacionService.obtenerCuentasDeReceptoresDeDonacion();
        } catch (ControlledException ce) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
