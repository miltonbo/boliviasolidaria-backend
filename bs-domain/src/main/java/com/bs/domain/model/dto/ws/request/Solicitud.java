package com.bs.domain.model.dto.ws.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Solicitud {
    
    private String nombre;
    private String direccion;
    private String necesidad;
    private String contacto;
    private String ci;
    private double lat;
    private double lng;
    
}
