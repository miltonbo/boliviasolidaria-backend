package com.bs.domain.model.dto.ws.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Milton BO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PuntoSolicitud {
    
    private int id;
    private double lat;
    private double lng;
    
}
