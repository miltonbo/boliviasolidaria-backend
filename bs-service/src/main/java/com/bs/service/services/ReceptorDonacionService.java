package com.bs.service.services;

import com.bs.domain.exception.ControlledException;
import com.bs.domain.utils.SystemPropertyUtil;
import com.bs.service.services.interfaces.IReceptorDonacionService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Milton BO
 */
@Service
public class ReceptorDonacionService implements IReceptorDonacionService {

    private static final Logger log = Logger.getLogger(ReceptorDonacionService.class.getName());


    @Override
    public String obtenerCuentasDeReceptoresDeDonacion() throws ControlledException {
        try {
            String archivoJson = SystemPropertyUtil.getResourcesPath() + "receptores-donacion/receptores.json";
            return FileUtils.readFileToString(new File(archivoJson), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error al leer contenido del archivo json de receptores", e);
            throw new ControlledException("Problemas al leer archivo de receptores.");
        }
    }
}
