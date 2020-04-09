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
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/{id}/logo", method = RequestMethod.GET)
    @ResponseBody public FileSystemResource obtenerLogo(@PathVariable Integer id,
                                                     HttpServletResponse response) {
        String imageFilename = "receptores-donacion/" + id + ".jpg";
        File file = new File(SystemPropertyUtil.getResourcesPath() + imageFilename);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        if (file.exists()) {
            return new FileSystemResource(file);
        }

        String carImageNotFound = SystemPropertyUtil.getResourcesPath() + "receptores-donacion/image-not-found.jpg";
        return new FileSystemResource(new File(carImageNotFound));
    }

}
