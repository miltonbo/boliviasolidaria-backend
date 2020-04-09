package com.bs.service.services;

import com.bs.domain.model.entities.PuntoAcopio;
import com.bs.service.services.interfaces.IPuntoAcopioService;
import com.bs.service.services.repositories.PuntoAcopioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author Milton BO
 */
@Service
public class PuntoAcopioService implements IPuntoAcopioService {

    private static final Logger log = Logger.getLogger(PuntoAcopioService.class.getName());

    @Autowired
    private PuntoAcopioRepository puntoAcopioRepository;


    @Override
    public List<PuntoAcopio> obtenerListaPuntosDeAcopio() {
        return puntoAcopioRepository.findAll();
    }
}
