package com.bs.api.endpoints;

import com.bs.domain.exception.ControlledException;
import com.bs.domain.model.dto.ws.response.RespuestaGeneral;
import com.bs.domain.model.entities.Solicitud;
import com.bs.domain.utils.HardCodeUtil;
import com.bs.domain.utils.ImageUtils;
import com.bs.domain.utils.SystemPropertyUtil;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
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

    @Autowired
    private SolicitudRepository solicitudRepository;

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

    @PostMapping
    private Object registrarSolicitud(@RequestBody Solicitud solicitud) {
        try {
            log.info("Creando nueva solicitud, datos: " + solicitud);
            return solicitudService.registrarSolicitud(solicitud);
        } catch (ControlledException ce) {
            return new ResponseEntity<>(new RespuestaGeneral(ce.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/{id}/ci-foto")
    private Object subirFotoDeCI(@RequestParam("foto") MultipartFile foto,
                                 @PathVariable Integer id) {
        if (foto.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            log.info("Guardando una foto de CI para la solicitud: " + id + ", longitud: " + foto.getSize());
            Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);
            if (! solicitudOptional.isPresent())
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            byte[] bytes = foto.getBytes();
            String filename = id + "-" + (new Date().getTime()) + ".jpg";
            String fullFilename = SystemPropertyUtil.getResourcesPath() + "ci/" + filename;
            ImageUtils.scaleImage(new ByteArrayInputStream(bytes), HardCodeUtil.RESIZED_WIDTH_CI_IMAGE,
                    HardCodeUtil.RESIZED_HEIGHT_CI_IMAGE, new File(fullFilename));
            log.log(Level.INFO, "Imagen subida para la solicitud: {0}", id);
            return "";
        } catch (IOException e) {
            log.log(Level.SEVERE, "Problemas al registrar la foto del CI", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
